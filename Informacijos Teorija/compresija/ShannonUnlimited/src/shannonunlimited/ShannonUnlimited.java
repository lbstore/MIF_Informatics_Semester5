/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shannonunlimited;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Lemmin
 */
public class ShannonUnlimited {
    public class DataTable{
        public short wordlen;
        public BigInteger slices;
        public LinkedList<CodeTableEntry> list;
        public String leftOver = "";
        public DataTable(int wl){
            this.wordlen = (short) wl;
        }
    }
    public class CodeTableEntry implements Comparable<CodeTableEntry>{
        public BigInteger ammount;
        public BigInteger dividedBy;
        public String binaryRepresentation;
        public String codeAs;
        public int l;
        public CodeTableEntry(BigInteger ammount, BigInteger dividedBy, String binRep){
            this.ammount = ammount;
            this.dividedBy = dividedBy;
            this.binaryRepresentation = binRep;
        }
        @Override
        public String toString(){
            return this.binaryRepresentation+" \t"+l+" \t" +ammount + "/"+dividedBy +"\t\t"+codeAs;
        }
        @Override
        public int compareTo(CodeTableEntry t) {
            return t.ammount.compareTo(ammount);
        }
    }
    public String fileIn;
    public String fileOut;
    public BinaryInputStream in;
    public BinaryOutputStream out;
    
    
    
    
    public DataTable analyze(int wordLength) throws IOException{
        DataTable data = new DataTable(wordLength);
        HashMap<String,BigInteger> map = new HashMap<>();
        data.slices = BigInteger.ZERO;
        LinkedList<CodeTableEntry> list = new LinkedList<>();
        while(!in.isEmpty()){
            String rep = in.readBitsAsString(data.wordlen);
            if(rep.contains("x")){//truncated word
                rep = rep.substring(0, rep.indexOf("x"));
                data.leftOver = rep;
                break;
            }
            if(map.containsKey(rep)){
                BigInteger bi = map.get(rep).add(BigInteger.ONE);
                map.put(rep, bi);
            }else{
                map.put(rep, BigInteger.ONE);
            }
            data.slices = data.slices.add(BigInteger.ONE);
        }
        for(String key:map.keySet()){
            CodeTableEntry entry = new CodeTableEntry(map.get(key),data.slices,key);
            entry.l = calculateL(entry.ammount,entry.dividedBy);
            list.add(entry);
        }
        Collections.sort(list);
        data.list = computeCodes(list);
        in.close();
        return data;
        
    }
    
    
    public void compress(DataTable data) throws IOException{
        
        in.close();
        in = new BinaryInputStream(this.fileIn);
        short wordLen = data.wordlen; //slice length
        out.write(wordLen);
        int len = data.list.size();  //different words
        out.write(len);                    
        
        short leftOverSize = (short) data.leftOver.length();//ammount of uncompressed Raw bits
        out.write(leftOverSize);  
        out.writeStringAsBits(data.leftOver);
        
        
        HashMap <String,String> map = new HashMap<>();
        for(CodeTableEntry entry:data.list){
            out.writeStringAsBits(entry.binaryRepresentation); // write variable word length code
            short codeLength = (short) entry.codeAs.length();
            out.write(codeLength);// write ammount of that entry
            out.writeStringAsBits(entry.codeAs);
            map.put(entry.binaryRepresentation, entry.codeAs);
        }
        
        BigInteger totalOfWords = data.slices;
        while(true){
            int cmp = totalOfWords.compareTo(BigInteger.ZERO);
            if (cmp <= 0){
                break;
            }else{
                totalOfWords = totalOfWords.subtract(BigInteger.ONE);
                String bits = in.readBitsAsString(data.wordlen);
                String codeAs = map.get(bits);
                out.writeStringAsBits(codeAs);
            }
        }
        out.close();
        
    }
    public void expand(){
        short wordLen = in.readShort(); //slice length
        int len = in.readInt();   //different entries
                           
        short leftOverSize = in.readShort(); //ammount of uncompressed Raw bits
        String leftOver = in.readBitsAsString(leftOverSize);// uncompressed bits
        HashMap <String,String> map = new HashMap<>();//decoding map
        for (int i=0; i<len; i++){
            String binaryRepresentation = in.readBitsAsString(wordLen);
            short codeAsLength = in.readShort();
            String codeAs = in.readBitsAsString(codeAsLength);
            map.put(codeAs, binaryRepresentation);
        }
        String word = "";
        while(!in.isEmpty()){
            boolean b = in.readBoolean();
            if(b){
                word+="1";
            }else{
                word+="0";
            }
            if(map.containsKey(word)){//found Word
                
                out.writeStringAsBits(map.get(word));
                word = "";
            }
        }
        out.writeStringAsBits(leftOver);
        out.flush();
        out.close();
        
        
    }
    public static String fractionToBinaryString(BigInteger top, BigInteger bottom, int length){
        int powerOf2 = 1;
        String repr = "";
        while(repr.length()<length){
            BigInteger currentBottom = BigInteger.valueOf(2).pow(powerOf2);
            BigInteger topBinary = bottom.multiply(BigInteger.ONE);
            BigInteger topFraction = top.multiply(currentBottom);
            int compare = topBinary.compareTo(topFraction);
            if(compare<=0){
                topFraction = topFraction.subtract(topBinary);
                top = topFraction;
                bottom = bottom.multiply(currentBottom);
                repr+="1";
            }else{
                repr+="0";
            }
            powerOf2+=1;
//            System.out.println(bottom+" "+currentBottom+" "+topBinary +" "+top + " "+ topFraction);
            
        }
        return repr;
        
    }    
    
    public LinkedList<CodeTableEntry> computeCodes(LinkedList<CodeTableEntry> list){
        BigInteger currentAmmount = BigInteger.ZERO;
        for(CodeTableEntry entry:list){
            String code = ShannonUnlimited.fractionToBinaryString(currentAmmount,entry.dividedBy,entry.l+1);
            entry.codeAs = code.substring(0, entry.l);
            currentAmmount = currentAmmount.add(entry.ammount);
        }
        return list;
    }
    public static int calculateL(BigInteger ammount, BigInteger total){
        int times = 0;
        BigInteger newTop = ammount.multiply(BigInteger.ONE);
        int cmp = total.compareTo(newTop);
        while(cmp==1){
            newTop = newTop.multiply(BigInteger.valueOf(2));
            times+=1;
            cmp = total.compareTo(newTop);
        }
        return times;
    }


    public ShannonUnlimited(String fin,String fout) throws IOException{
        in = new BinaryInputStream(fin);
        this.fileIn = fin;
        out = new BinaryOutputStream(fout);
    }
}
