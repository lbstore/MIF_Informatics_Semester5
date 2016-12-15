/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compression;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import javafx.concurrent.Task;
import lib.BinaryStdIn;
import lib.BinaryStdOut;

/**
 *
 * @author Lemmin
 */
public class Shannon {
    // alphabet size of extended ASCII
    
    public class DataTable{
        public int wordlen;
        public long ammountOf;
        public long sourceInBinarySize;
        public LinkedList<String> sourceInBinary;
        public HashMap<String,CodeTableEntry> map;
        public LinkedList<CodeTableEntry> list;
        public String leftOver = "";
        public DataTable(int wl){
            this.wordlen = wl;
            this.sourceInBinary = new LinkedList<>();
            this.map = new HashMap<>();
        }
    }
    public class CodeTableEntry implements Comparable<CodeTableEntry>{
        public long ammount;
        public long dividedBy;
        public String binaryRepresentation;
        public String codeAs;
        public int l;
        public CodeTableEntry(long ammount, long dividedBy, String binRep){
            this.ammount = ammount;
            this.dividedBy = dividedBy;
            this.binaryRepresentation = binRep;
        }
        @Override
        public String toString(){
            return this.binaryRepresentation+" \t"+l+" \t" +ammount + "/"+dividedBy +"\t"+codeAs;
        }
        @Override
        public int compareTo(CodeTableEntry t) {
            return Long.compare(t.ammount, ammount);
        }
    }
    public String tempFile;
    public BinaryStdIn tempIn;
    public BinaryStdOut tempOut;
    public BinaryStdIn in;
    public BinaryStdOut out;
    
    
    
    public DataTable readInput(int wordLength) throws IOException{
        DataTable data = new DataTable(wordLength);
        data.wordlen = wordLength;
        HashMap<String,Integer> map = new HashMap<>();
        data.ammountOf = 0;
        LinkedList<CodeTableEntry> list = new LinkedList<>();
        while(!in.isEmpty()){
            String rep = in.readBitsAsString(data.wordlen);
            if(rep.contains("x")){//truncated word
                rep = rep.substring(0, rep.indexOf("x"));
                data.leftOver = rep;
                break;
            }
            if(map.containsKey(rep)){
                int i = map.get(rep )+1;
                map.put(rep, i);
            }else{
                map.put(rep, 1);
            }
            data.ammountOf++;
            data.sourceInBinary.add(rep);
        }
        for(String key:map.keySet()){
            CodeTableEntry entry = new CodeTableEntry(map.get(key),data.ammountOf,key);
            entry.l = calculateL(entry.ammount,entry.dividedBy);
            list.add(entry);
        }
        Collections.sort(list);
        list = computeCodes(list);
        for(CodeTableEntry entry:list){
            data.map.put(entry.binaryRepresentation, entry);
        }
        data.list = list;
        in.close();
        return data;
        
    }
    public DataTable readInputUpdate(int wordLength) throws IOException{
        DataTable data = new DataTable(wordLength);
        data.wordlen = wordLength;
        HashMap<String,Integer> map = new HashMap<>();
        data.ammountOf = 0;
        data.sourceInBinarySize = 0;
        this.tempOut = new BinaryStdOut(tempFile);
        LinkedList<CodeTableEntry> list = new LinkedList<>();
        while(!in.isEmpty()){
            String rep = in.readBitsAsString(data.wordlen);
            if(rep.contains("x")){//truncated word
                rep = rep.substring(0, rep.indexOf("x"));
                data.leftOver = rep;
                break;
            }
            if(map.containsKey(rep)){
                int i = map.get(rep )+1;
                map.put(rep, i);
            }else{
                map.put(rep, 1);
            }
            data.ammountOf++;
            data.sourceInBinarySize++;
        }
        for(String key:map.keySet()){
            CodeTableEntry entry = new CodeTableEntry(map.get(key),data.ammountOf,key);
            entry.l = calculateL(entry.ammount,entry.dividedBy);
            list.add(entry);
        }
        Collections.sort(list);
        list = computeCodes(list);
        for(CodeTableEntry entry:list){
            data.map.put(entry.binaryRepresentation, entry);
        }
        data.list = list;
        in.close();
        return data;
        
    }
    
    
    
    public void compress(DataTable data){
        int len =  data.map.size();
        out.write(len);// ammount of entries
        out.write(data.wordlen);//word length
        out.write(data.ammountOf); //amount of words
        out.write(data.leftOver.length());//ammount of uncompressed Raw bits
        out.writeStringAsBits(data.leftOver);
        for(CodeTableEntry entry:data.list){
            out.writeStringAsBits(entry.binaryRepresentation); // write variable word length code
            out.write(entry.ammount);// write ammount of that entry
        }
        for(String bin :data.sourceInBinary){
            CodeTableEntry entry = data.map.get(bin);
            out.writeStringAsBits(entry.codeAs);
        }
        
        out.flush();
        out.close();
    }
    
    public Task<Void> getCompressTask(DataTable data){
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                int len =  data.map.size();
                out.write(len);// ammount of entries max is 2^wordlen INT
                out.write(data.wordlen);//word length INT
                out.write(data.ammountOf); //amount of words LONG
                out.write(data.leftOver.length());//ammount of uncompressed Raw bits INT
                out.writeStringAsBits(data.leftOver);
                long progress = 0;
                long totalProgress = data.list.size() + data.sourceInBinary.size();

                for(CodeTableEntry entry:data.list){
                    out.writeStringAsBits(entry.binaryRepresentation); // write variable word length code
                    out.write(entry.ammount);// write ammount of that entry
//                    progress++;
//                    this.updateProgress(progress, totalProgress);
                }
                for(String bin :data.sourceInBinary){
                    CodeTableEntry entry = data.map.get(bin);
                    out.writeStringAsBits(entry.codeAs);
//                    progress++;
//                    this.updateProgress(progress, totalProgress);
                }

//                out.flush();
//                out.close();
                return null;
            }
        };
        return task;
    }
    public Task<Void> getCompressTaskUpdate(DataTable data){
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                int len =  data.map.size();
                out.write(len);// ammount of entries max is 2^wordlen INT
                out.write(data.wordlen);//word length INT
                out.write(data.ammountOf); //amount of words LONG
                out.write(data.leftOver.length());//ammount of uncompressed Raw bits INT
                out.writeStringAsBits(data.leftOver);
                int progress = 0;
                long totalProgress = data.list.size() + data.sourceInBinarySize;
                
                for(CodeTableEntry entry:data.list){
                    out.writeStringAsBits(entry.binaryRepresentation); // write variable word length code
                    out.write(entry.ammount);// write ammount of that entry
                    progress++;
                    this.updateProgress(progress, totalProgress);
                }
                tempIn = new BinaryStdIn(tempFile);
                while(true){
                    if(tempIn.isEmpty()){
                        break;
                    }
                    
                    String bin = tempIn.readBitsAsString(data.wordlen);
                    CodeTableEntry entry = data.map.get(bin);
                    out.writeStringAsBits(entry.codeAs);
                    progress++;
                    this.updateProgress(progress, totalProgress);
                    
                }
                Files.deleteIfExists(Paths.get(tempFile));
                out.flush();
                out.close();
                return null;
            }
        };
        return task;
    }
    
    public DataTable readInfoCompressed(){       
        LinkedList<CodeTableEntry> list = new LinkedList<>();
        int len = in.readInt();// ammount of entries
        int wordLen = in.readInt();//word length
        long ammount = in.readLong(); //amount of words
        int leftOverLen = in.readInt(); //left over length
        String leftOver = "";
        for(int i=0; i<leftOverLen; i++){
            boolean b = in.readBoolean();
                if(b){
                    leftOver+="1";  
                }else{
                    leftOver+="0";
                }
        }
        DataTable data = new DataTable(wordLen);
        data.leftOver = leftOver;
        data.ammountOf = ammount;
        for(int j= 0; j<len; j++){
            String word = "";
            for(int i= 0; i<wordLen; i++){
                boolean b = in.readBoolean();
                if(b){
                    word+="1";  
                }else{
                    word+="0";
                }
            }
            long entryAmmount = in.readLong();
            CodeTableEntry entry = new CodeTableEntry(entryAmmount,ammount,word);
            entry.l = calculateL(entry.ammount,entry.dividedBy);
            list.add(entry);
        }
        
        Collections.sort(list);
        list = computeCodes(list);
        
        for(CodeTableEntry entry:list){
            data.map.put(entry.codeAs, entry);
        }
        data.list = list;
        return data;
    }
    public Task<Void> getExpandTask(DataTable data){
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                int progress = 0;
                long totalProgress = data.list.size() + data.sourceInBinarySize;
                String word = "";
                HashMap<String,String> map = new HashMap<>();
                for(CodeTableEntry entry:data.map.values()){
                    map.put(entry.codeAs, entry.binaryRepresentation);
                }
//                data.list.clear();
//                data.map.clear();
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
                        progress++;
                        this.updateProgress(progress, totalProgress);
                    }
                    
                }
                out.writeStringAsBits(data.leftOver);
                out.flush();
                out.close();

                return null;  
            };
        };
        return task;
    }
    public void expand(DataTable data){
        String word = "";
        HashMap<String,String> map = new HashMap<>();
        for(CodeTableEntry entry:data.map.values()){
            map.put(entry.codeAs, entry.binaryRepresentation);
        }
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
        out.writeStringAsBits(data.leftOver);
        out.flush();
        out.close();
        
    }
    public static long power(long n, int p){
        long multiplier = n;
        for(int i=1; i<p; i++){
           n*= multiplier; 
        }
        return n;
    }
    public static String factionToString(long top, long bottom, int length){
        int powerOf2 = 1;
        String repr = "";
        while(repr.length()<length){
            
            long currentBottom = power(2,powerOf2);
            long topBinary = 1*bottom;
            long topFraction = top*currentBottom;
            
            if(topBinary<=topFraction){
                topFraction -= topBinary;
                top = topFraction;
                bottom = currentBottom*bottom;
                repr+="1";
            }else{
                repr+="0";
            }
            powerOf2+=1;
//            System.out.println(bottom+" "+currentBottom+" "+topBinary +" "+top + " "+ topFraction);
            
        }
        return repr;
    }
    public static String fractionToBinaryString(long topL, long bottomL, int length){
        int powerOf2 = 1;
        String repr = "";
        BigInteger top = BigInteger.valueOf(topL);
        BigInteger bottom = BigInteger.valueOf(bottomL);
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
        long currentAmmount = 0;
        
        for(CodeTableEntry entry:list){
            String code = Shannon.fractionToBinaryString(currentAmmount,entry.dividedBy,entry.l+1);
            entry.codeAs = code.substring(0, entry.l);
            currentAmmount+=entry.ammount;
        }
        return list;
    }
    public static int calculateL(long ammount, long total){
        int times = 0;
        long newTop = ammount;
        while(total> newTop){
            newTop = 2*newTop;
            times+=1;
        }
        return times;
    }


    public Shannon(String fin,String fout) throws IOException{
        in = new BinaryStdIn(fin);
        tempFile = fin+".temp";
        out = new BinaryStdOut(fout);
    }
}
