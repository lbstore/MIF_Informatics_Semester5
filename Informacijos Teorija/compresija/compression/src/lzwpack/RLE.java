/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lzwpack;

/* "RLE.java" */

import java.io.*;

/** The Run Length Encoding (RLE) algorithm compresses any sequence of byte data.
 * The resulting compressed codes are larger than bytes,
 * but can each represent a group of bytes,
 * at least some of the time,
 * for an overall saving.
 * <P>
 * RLE compression recognizes sequences of a repeating value,
 * and replaces each sequence by a code,
 * representing the value and the repeat count.
 * The degree of compression depends on how much repetition is found in the data.
 * RLE compression is effective with data having many and / or lengthy
 * <B>contiguous repeat values,</B>
 * such as graphics with areas of solid color,
 * and sparsely filled binary data with runs of zero bytes.
 * <P>
 * The repeat count should allow for the expected amount of repetition,
 * without wasting too much space allowing for unusually large counts,
 * which can be handled when they do occur,
 * by writing one or more additional codes.
 * This version uses 4 bit counts for total 12 bit code width.
 * <P>
 * <B>Note!</B>
 * Other versions of RLE may use other sizes of repeat count,
 * and other coding conventions.
 * They are not inter-operable with this algorithm.
 * <P>
 * Tsukiyama has two US patents on run length encoding:
 * 4,586,027 and 4,872,009 granted in 1986 and 1989 respectively.
 * <UL>
 * <LI>4,586,027
 * Method and system for data compression and restoration
 * inventor Tsukimaya et al.
 * assignee Hitachi
 * filed 08/07/84, granted 04/29/86
 * patents run length encoding in its most primitive form:
 * a length byte followed by the repeated byte.
 * <LI>4,872,009
 * Method and apparatus for data compression and restoration
 * inventor Tsukimaya et al.
 * assignee Hitachi
 * filed 12/07/87, granted 10/03/89
 * This patent on run length encoding covers the invention of limiting
 * the run length to 16 bytes,
 * and thus the encoding of the length on 4 bits.
 * </UL>
 * <P>
 * There is no need to construct an RLE object,
 * the RLE.Compress () and RLE.Expand () methods are static.
 */

public class RLE {

/** Construct an RLE compressor / expander. */

  public RLE () { }

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

/** Compress a sequence of raw data bytes.
 * @param inRaw source of data bytes to be compressed,
 * e.g. ByteArrayInputStream or BufferedInputStream.
 * @param outComp packer for destination for compressed data,
 * either to a stream or an array.
 * @exception IOException from either underlying stream.
 */

    public static int Compress (InputStream inRaw, CodeOutputPacker outComp)
    throws IOException {

	int inctr = 0;

/* The value byte is returned as an int in the range 0 to 255.
 * It does --not-- have to be masked. */

        int oldByte = inRaw.read ();
	inctr++;

//System.out.println ("first="+(char)oldByte);

	int rptCt = 0;

        int oneByte;
        while (-1 != (oneByte = inRaw.read ())) {
	    inctr++;

//System.out.println ("="+(char)oneByte);

	    if ((oldByte == oneByte) && (rptCt < 14))
		rptCt++;

            else {

                outComp.putN ((rptCt << 8) + (int)oldByte);

                oldByte = oneByte;
		rptCt = 0;
            }
        }

/* Output last byte directly,
 * since nothing follows to force it out,
 * and flush in case we are on odd half. */

        outComp.putN ((rptCt << 8) + (int)oldByte);
        outComp.flush ();

	return inctr;
    }

/** Compress a sequence of raw data bytes.
 * @param inRaw array of data bytes to be compressed,
 * @param outComp packer for destination for compressed data,
 * either to a stream or an array.
 * @exception IOException from either underlying stream.
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
 * @exception IOException from either underlying stream.
 */

    public static int Compress (byte [] inRaw, int validCount, CodeOutputPacker outComp)
    throws IOException {

        int inctr = 0;

/* The value byte is cosidered signed and gets extended.
 * It --does-- have to be masked to be considered 0-255. */

        int oldByte = inRaw [inctr++];
	oldByte &= 0x00ff;

//System.out.println ("first="+(char)oldByte);

	int rptCt = 0;

        int oneByte;
	while ((inctr < inRaw.length) && (inctr < validCount)) {
            oneByte = inRaw [inctr++];
	    oneByte &= 0x00ff;

//System.out.println ("="+(char)oneByte);

	    if ((oldByte == oneByte) && (rptCt < 14))
		rptCt++;

            else {

                outComp.putN ((rptCt << 8) + (int)oldByte);

                oldByte = oneByte;
		rptCt = 0;
            }
        }

/* Output last byte directly,
 * since nothing follows to force it out,
 * and flush in case we are on odd half. */

        outComp.putN ((rptCt << 8) + (int)oldByte);
        outComp.flush ();

	return inctr;
    }

/** Expand Compressed codes back to raw data.
 * This will produce garbage if input was not RLE compressed data,
 * but the algorithm has no way of testing for this.
 * @param inComp unpacker from source.
 * @param outExp destination for decoded bytes.
 * @return count of decoded bytes.
 * @exception IOException from either underlying stream.
 */

    public static int Expand (CodeInputUnpacker inComp, OutputStream outExp)
    throws IOException {

          int outctr = 0;

/* loop while more input.
 * each code provides a byte value to be output one or more times.
 * zero repeats means one output. */

	  while (!inComp.atEnd ()) {
              int newCode = inComp.getN ();

	      int repCt = (newCode >> 8) & 0x0f;
	      byte bVal = (byte)(newCode & 0xff);

//System.out.println ("rep="+repCt+" ch=<"+(char)bVal+">");

              for (int ii = 0; ii < repCt+1; ii++) {
                  outExp.write (bVal);
		  outctr++;
	      }
          }

        return outctr;
    }

/** Expand Compressed codes back to raw data.
 * This will produce garbage if input was not RLE compressed data,
 * but the algorithm has no way of testing for this.
 * @param inComp unpacker from source.
 * @param outExp destination for decoded bytes.
 * @return count of decoded bytes.
 * @exception IOException from either underlying stream.
 */

    public static int Expand (CodeInputUnpacker inComp, byte [] outExp)
    throws IOException {

	  int outctr = 0;

/* loop while more input.
 * each code provides a byte value to be output one or more times.
 * zero repeats means one output. */

	  while (!inComp.atEnd ()) {
              int newCode = inComp.getN ();

	      int repCt = (newCode >> 8) & 0x0f;
	      byte bVal = (byte)(newCode & 0xff);

//System.out.println ("rep="+repCt+" ch=<"+(char)bVal+">");

              for (int ii = 0; ii < repCt+1; ii++)
                  outExp [outctr++] = bVal;
          }

	return outctr;
    }

/** For test and demonstration.
 * From a built-in string and from any named files.
 * Test files may be text or binary,
 * but be prepared for the binary output. */

    public static void main (String [] argv) {

        int nComp, nExp;

        try {
            ByteArrayInputStream bais
             = new ByteArrayInputStream
               (("1 12 123\n"
               +"a ab abc abcd abcde abcdef\n"
               +"b bc bcd bcde bcdef\n"
               +"a ab abc abcd abcde abcdef\n"
               +"b bc bcd bcde bcdef\n"
               +"c cd cde cdef").getBytes ());

            CodeOutputPacker baos = new CodeOutputPacker (5000);

            nComp = RLE.Compress (bais, baos);

            nExp = RLE.Expand (new CodeInputUnpacker
                           (baos.toByteArray ()),
                   System.out);

            System.out.println ("\nOK "+nComp+" -> "+nExp);

        } catch (Exception ex) { ex.printStackTrace (); }
        System.out.println ("\n-------");

        try {
            byte [] rawbytes
             = ("1 12 123\n"
               +"a ab abc abcd abcde abcdef\n"
               +"b bc bcd bcde bcdef\n"
               +"a ab abc abcd abcde abcdef\n"
               +"b bc bcd bcde bcdef\n"
               +"c cd cde cdef").getBytes ();

            CodeOutputPacker baos = new CodeOutputPacker (5000);

            nComp = RLE.Compress (rawbytes, baos);

            byte [] packedbytes = baos.toByteArray ();

            nExp = RLE.Expand (new CodeInputUnpacker (packedbytes),
                        System.out);

            System.out.println ("\nOK "+nComp+" -> "+nExp);

        } catch (Exception ex) { ex.printStackTrace (); }
        System.out.println ("\n-------");

        for (int argc = 0; argc < argv.length; argc++) {
            try {
                BufferedInputStream bis
                 = new BufferedInputStream
                     (new FileInputStream
                         (new File (argv [argc])));

                CodeOutputPacker baos = new CodeOutputPacker (250000);

                nComp = RLE.Compress (bis, baos);

                nExp = RLE.Expand (new CodeInputUnpacker
                           (baos.toByteArray ()),
                   System.out);

                System.out.println ("\nOK:"+argv [argc]+" "+nComp+" -> "+nExp);

            } catch (Exception ex) { ex.printStackTrace (); }
            System.out.println ("\n-------");
        }
    }

}
/* <IMG SRC="/cgi-bin/counter">*/
///////////////////////////////////////////////////////