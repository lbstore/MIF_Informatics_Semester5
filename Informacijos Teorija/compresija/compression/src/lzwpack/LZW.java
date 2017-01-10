package lzwpack;
/* "LZW.java" */

import java.io.*;

/** The Limpel, Ziv, Welch (LZW) algorithm compresses any sequence of byte data.
 * The resulting compressed codes are larger than bytes,
 * but can each represent a group of bytes,
 * at least some of the time,
 * for what is usually an overall saving.
 * <P>
 * LZW compression recognizes sequences that have already appeared in the data,
 * and replaces each later appearance by a unique code.
 * The degree of compression depends on how much repetition is found in the data.
 * LZW compression is effective with data having many and / or lengthy
 * <B>repeat sequences,</B>
 * such as text,
 * and graphics with areas of solid color or patterns.
 * <P>
 * The sequence history should allow for the expected amount of repetition,
 * without wasting too much space in each code on a history index.
 * This version is fixed at 12 bit codes,
 * which can refer to nearly a 2^^12 item sequence history,
 * less some internal overhead.
 * This size is considered good for short message blocks,
 * and is simple to implement.
 * <P>
 * <B>Note!</B>
 * Other versions of LZW may use other sizes of sequence history,
 * and other coding conventions.
 * They are not inter-operable with this algorithm.
 * <P>
 * <B>The original papers:</B>
 * <UL>
 * <LI>Terry Welch,
 * A Technique for High-Performance Data Compression,
 * IEEE Computer, June 1984
 * <LI>J. Ziv and A. Lempel,
 * A Universal Algorithm for Sequential Data Compression,
 * IEEE Transactions on Information Theory, May 1977
 * </UL>
 * This and many similar algorithms have been patented:
 * <UL>
 * <LI>One form of the original LZ78 algorithm was patented (4,464,650)
 * by its authors Lempel, Ziv, Cohn and Eastman. This patent is owned by Unisys.
 * <LI>The LZW algorithm used in 'compress' is patented,
 * both by IBM (4,814,746) and Unisys (4,558,302).
 * </UL>
 * 
 * This Java was ported from C code in the copyrighted article,
 * "LZW Data Compression" by Mark Nelson,
 * in <A HREF="http://www.ddj.com" >Dr. Dobb's Journal</A>
 * October, 1989.
 * <P>
 * There is no need to construct an LZW object,
 * the LZW.Compress () and LZW.Expand () methods are static.
 */

public class LZW {

/** Note some Java types differ from C types:
 * <P>
 * byte (8), short (16), int (32) are signed, there is no unsigned.
 * therefore positive (unsigned) values 0-255 need short not byte,
 * at least while we are actively using them.
 * OK to --store-- them in byte with casting and masking.
 * <P>
 * char (16) not (8) to support Unicode,
 * so char is not the same as byte (8).
 * neither is it the same as short (16).
 * <P>
 * <B>int InputStream.read ()</B>
 * Reads the next byte of data from this input stream.
 * The value byte is returned as an int in the range 0 to 255.
 * If no byte is available because the end of the stream has been reached,
 * the value -1 is returned.
 * This method blocks until input data is available,
 * the end of the stream is detected,
 * or an exception is thrown. */

/* SHIFTCOUNT = BITS-8 used for hashing (why this number?)
 * MAXVALUE largest below 2^^BITS is reserved as a sentinel.
 * TABLESIZE must be prime larger than MAXVALUE for hashing to work.
 * With MAXVALUE = 4094 the next prime is 5021 */

    private static final int
       SHIFTCOUNT = 4, 
       MAXVALUE   =  (1 << 12) - 1, 
       MAXCODE    = MAXVALUE - 1, 
       TABLESIZE  = 5021;

/** Compress a sequence of raw data bytes.
 * @param inRaw source of data bytes to be compressed,
 * e.g. ByteArrayInputStream or BufferedInputStream.
 * @param outComp packer for destination for compressed data,
 * either to a stream or an array.
 * @return number of raw data bytes compressed.
 * @exception IOException from either underlying stream.
 */

    public static int Compress (InputStream inRaw, CodeOutputPacker outComp)
    throws IOException {

        int inctr = 0;

/* codes 0-255 represent themselves as possible byte values */

        int nextCode = 256;

        LZWHistoryItem [] history = new LZWHistoryItem [TABLESIZE];

/* get the first data byte then loop for the rest of them.
 * This first data byte will be saved as Prefix,
 * and the second data byte as Append,
 * for nextCode=256. */

/* The value byte is returned as an int in the range 0 to 255.
 * It does --not-- have to be masked. */

        int oneCode = inRaw.read ();
        inctr++;

        int oneByte;
        while (-1 != (oneByte = inRaw.read ())) {
            inctr++;

/* see if the table already has this prefix//char pair */

            int index = FindMatch (history, oneCode, oneByte);

/* yes at this index,
 * get the code for it as the --next-- prefix,
 * so we can try to append the next byte */

            if (history [index] != null)
                oneCode = history [index].Code;

/* no,
 * output the code for the last data string,
 * and start trying to grow onto the new one.
 *
 * this table index is vacant.
 * The hash table always has some extra room,
 * because MAXCODE < TABLESIZE.
 *
 * if not already at MAXCODE,
 * add prefix//char pair as nextCode number,
 * otherwise we cannot save anymore.  */

            else {
                outComp.putN (oneCode);

                if (nextCode <= MAXCODE)
                    history [index]
                     = new LZWHistoryItem (nextCode++, oneCode, oneByte);

                oneCode = oneByte;
            }

        }

/* Output last byte directly as a code < 256,
 * plus an end of buffer sentinel MAXVALUE code,
 * and flush in case we are on odd half. */

        outComp.putN (oneCode);
        outComp.putN (MAXVALUE);
        outComp.flush ();

        return inctr;
    }

/** Compress a sequence of raw data bytes.
 * @param inRaw array of data bytes to be compressed,
 * @param outComp packer for destination for compressed data,
 * either to a stream or an array.
 * @return number of raw data bytes compressed.
 * @exception IOException from underlying stream.
 */

    public static int Compress (byte [] inRaw, CodeOutputPacker outComp)
    throws IOException {
        return Compress (inRaw, inRaw.length, outComp);
    }

/** Compress a sequence of raw data bytes.
 * @param inRaw array of data bytes to be compressed,
 * @param validCount how much of inRaw array to be compressed,
 * @param outComp packer for destination for compressed data,
 * either to a stream or an array.
 * @return number of raw data bytes compressed.
 * @exception IOException from underlying stream.
 */

    public static int Compress (byte [] inRaw, int validCount, CodeOutputPacker outComp)
    throws IOException {

        int inctr = 0;

/* codes 0-255 represent themselves as possible byte values */

        int nextCode = 256;

        LZWHistoryItem [] history = new LZWHistoryItem [TABLESIZE];

/* get the first data byte then loop for the rest of them.
 * This first data byte will be saved as Prefix,
 * and the second data byte as Append,
 * for nextCode=256. */

/* The value byte is cosidered signed and gets extended.
 * It --does-- have to be masked to be considered 0-255. */

        int oneCode = inRaw [inctr++];
        oneCode &= 0x00ff;

// System.out.println ("first="+(char)oneCode);

        int oneByte;
        while ((inctr < inRaw.length) && (inctr < validCount)) {
            oneByte = (byte)inRaw [inctr++];
            oneByte &= 0x00ff;

// System.out.println ("="+(char)oneByte);

/* see if the table already has this prefix//char pair */

            int index = FindMatch (history, oneCode, oneByte);

/* yes at this index,
 * get the code for it as the --next-- prefix */

            if (history [index] != null)
                oneCode = history [index].Code;

/* no,
 * output the code for the last data string,
 * and start trying to grow onto the new one.
 *
 * this table index is vacant.
 * The hash table always has some extra room,
 * because MAXCODE < TABLESIZE.
 *
 * if not already at MAXCODE,
 * add prefix//char pair as nextCode number,
 * otherwise we cannot save anymore.  */

            else {
                outComp.putN (oneCode);

                if (nextCode <= MAXCODE)
                    history [index]
                     = new LZWHistoryItem (nextCode++, oneCode, oneByte);

                oneCode = oneByte;
            }
        }

/* Output last byte directly as a code < 256,
 * plus an end of buffer sentinel MAXVALUE code,
 * and flush in case we are on odd half. */

        outComp.putN (oneCode);
        outComp.putN (MAXVALUE);
        outComp.flush ();

        return inctr;
    }

/** Try to find a match for aPrefix//aCharacter,
 * return index of the match if found,
 * or a vacant slot.
 * Always possible to find a vacant slot,
 * because MAXCODE < TABLESIZE. */

/////// aPrefix 12-bits could use short
/////// aCharacter only needs 0-255 unsigned

    private static int FindMatch (LZWHistoryItem [] history, int aPrefix, int aCharacter) {
        int offset;

/* Hash index (somehow) based on both prefix and new char */

        int index = (aCharacter << SHIFTCOUNT) ^ aPrefix;

        if (index == 0)
            offset = 1;
        else
            offset = TABLESIZE - index;

        while (true) {

/* If either a vacant slot,
 * or a match (on both parts),
 * return the index. */

            if ((history [index] == null)
             || ((history [index].Prefix == aPrefix)
              && (history [index].Append == aCharacter)))
                return index;

/* Collision, try again...
 * Because TABLESIZE is prime we try everything. */

            index -= offset;
            if (index < 0) index += TABLESIZE;
        }
    }

/** Expand Compressed codes back to raw data.
 * This will fail if input was not LZW compressed data.
 * @param inComp unpacker from source.
 * @param outExp destination for decoded bytes.
 * @return count of decoded raw data bytes.
 * @exception IOException from either underlying stream.
 * @exception IllegalArgumentException if input was not LZW compressed data.
 */

    public static int Expand (CodeInputUnpacker inComp, OutputStream outExp)
    throws IOException, IllegalArgumentException {

        int outctr = 0;

        int nPushed;

        LZWByteStack charStack = new LZWByteStack ();

/* Will fill in parallel with Compress table,
 * so that each Code has same meaning in both. */

        LZWHistoryItem [] history = new LZWHistoryItem [TABLESIZE];

/* codes 0-255 already represent themselves as possible byte values */

        int nextCode = 256;

/* get first input code.
 * Remember 12 bit codes overlap bytes,
 * so every 2 calls will input 3 bytes. */

        int oldCode = inComp.getN ();

/* first input code must be a direct byte value 0-255,
 * (or we have the wrong input!) */

        if (oldCode > 255)
            throw new IllegalArgumentException ("Not LZW compressed data");

/* so move it to output buffer.
 * Also save it for the table. */

        int aChar = oldCode;

        outExp.write (aChar);
        outctr++;

/* loop while more input,
 * final code will be sentinel.
 * surely will fail along the way if not LZW compressed data. */

        try {
          int newCode;
          while (MAXVALUE != (newCode = inComp.getN ())) {

/* To check for the special STRING+CHARACTER+STRING+CHARACTER+STRING case,
 * if code value larger than yet seen (they increase),
 * we know it is not in the table,
 * so get expansion of the old code,
 * and append this code to it as a character,
 * by pushing it first onto the stack. */

              if (newCode >= nextCode) {
                  charStack.push (aChar);
                  nPushed = 1 + DecodeString (history, charStack, oldCode);
              }

/* newCode is already known in the table so expand it,
 * or it is an actual byte value < 256 */

              else
                  nPushed = DecodeString (history, charStack, newCode);

/* This top one will go into history table as the nextCode */

              outExp.write (aChar = charStack.pop ());
              outctr++;

/* The rest (if any more) are output in proper order */

              while (1 < nPushed--) {
                  outExp.write (charStack.pop ());
                  outctr++;
              }

/* Append to history table if we have room,
 * otherwise a missed chance for better compression,
 * but nothing will be lost,
 * because Compressor made the same decision. */

              if (nextCode <= MAXCODE) {
                  history [nextCode] = new LZWHistoryItem (nextCode, oldCode, aChar);
                  nextCode++;

                  oldCode = newCode;
              }
          }
        }
        catch (IOException eio) { throw eio; }
        catch (Exception e) {
        e.printStackTrace ();
        throw new IllegalArgumentException ("Not LZW compressed data"); }

        return outctr;
    }

/** Expand Compressed codes back to raw data.
 * This will fail if input was not LZW compressed data.
 * @param inComp unpacker from source.
 * @param outExp destination for decoded bytes.
 * @return count of decoded raw data bytes.
 * @exception IOException from either underlying stream.
 * @exception IllegalArgumentException if input was not LZW compressed data.
 */

    public static int Expand (CodeInputUnpacker inComp, byte [] outExp)
    throws IOException, IllegalArgumentException {

        int outctr = 0;

        int nPushed;

        LZWByteStack charStack = new LZWByteStack ();

/* Will fill in parallel with Compress table,
 * so that each Code has same meaning in both. */

        LZWHistoryItem [] history = new LZWHistoryItem [TABLESIZE];

/* codes 0-255 already represent themselves as possible byte values */

        int nextCode = 256;

/* get first input code.
 * Remember 12 bit codes overlap bytes,
 * so every 2 calls will input 3 bytes. */

        int oldCode = inComp.getN ();

/* first input code must be a direct byte value 0-255,
 * (or we have the wrong input!) */

        if (oldCode > 255)
            throw new IllegalArgumentException ("Not LZW compressed data");

/* so move it to output buffer.
 * Also save it for the table. */

        int aChar = oldCode;

        outExp [outctr++] = (byte)aChar;

/* loop while more input,
 * final code will be sentinel.
 * surely will fail along the way if not LZW compressed data. */

        try {
          int newCode;
          while (MAXVALUE != (newCode = inComp.getN ())) {

/* To check for the special STRING+CHARACTER+STRING+CHARACTER+STRING case,
 * if code value larger than yet seen (they increase),
 * we know it is not in the table,
 * so get expansion of the old code,
 * and append this code to it as a character,
 * by pushing it first onto the stack. */

              if (newCode >= nextCode) {
                  charStack.push (aChar);
                  nPushed = 1 + DecodeString (history, charStack, oldCode);
              }

/* newCode is already known in the table so expand it,
 * or it is an actual byte value < 256 */

              else
                  nPushed = DecodeString (history, charStack, newCode);

/* This top one will go into history table as the nextCode */

              outExp [outctr++] = (byte)(aChar = charStack.pop ());

/* The rest (if any more) are output in proper order */

              while (1 < nPushed--)
                  outExp [outctr++] = (byte)charStack.pop ();

/* Append to history table if we have room,
 * otherwise a missed chance for better compression,
 * but nothing will be lost,
 * because Compressor made the same decision. */

              if (nextCode <= MAXCODE) {
                  history [nextCode] = new LZWHistoryItem (nextCode, oldCode, aChar);
                  nextCode++;

                  oldCode = newCode;
              }
          }
        }
        catch (IOException eio) { throw eio; }
        catch (Exception e) { throw new IllegalArgumentException ("Not LZW compressed data"); }

        return outctr;
    }

    private static int DecodeString (LZWHistoryItem [] history, LZWByteStack cStack, int code) {
        int ii = 1;

/* codes 0-255 stand for themselves as actual byte values,
 * higher ones are indices of table entries.
 * add their Append values to the cStack,
 * running down Prefix chain until an actual byte value */

        while (code > 255) {
            cStack.push (history [code].Append);
            code = history [code].Prefix;
            ii++;
        }

/* finally add actual byte value.
 * We have added (ii) values in reverse order,
 * so our caller will have to unstack them. */

        cStack.push ((byte)code);

        return ii;
    }
}

/* Table items */

class LZWHistoryItem {
     int Code = -1;
     int Prefix = 0;
     int Append = 0;

     public LZWHistoryItem () { }

// Code and Prefix 12-bits could use short
// Append only needs 0-255 unsigned

     public LZWHistoryItem (int Code, int Prefix, int Append) {

//System.out.println ("new LZWHistoryItem "+Code+" "+Prefix+"="+(char)Append);
         this.Code = Code;
         this.Prefix = Prefix;
         this.Append = Append;
     }
}

/* Note that java.util.stack handles Objects not native types,
 * so it is neither convenient nor efficient here. */

class LZWByteStack {

// byte is enough for 0-255 problem is getting unsigned

    private int [] ints;

    private int ctr = 0;

    public LZWByteStack () { this (4000); }

    public LZWByteStack (int size) { ints = new int [size]; }

    public void push (int bv) { ints [ctr++] = bv; }

    public int pop () { return ints [--ctr]; }
}
/* <IMG SRC="/cgi-bin/counter">*/
///////////////////////////////////////////////////////
