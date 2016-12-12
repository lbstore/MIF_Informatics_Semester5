/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compression;

import java.io.IOException;
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
        public int ammountOf;
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
        public int ammount;
        public int dividedBy;
        public String binaryRepresentation;
        public String codeAs;
        public int l;
        public double getProbability(){
            return (double)ammount/dividedBy;
        }
        public CodeTableEntry(int ammount, int dividedBy, String binRep){
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
            return t.ammount - this.ammount;
        }
    }
  
    public BinaryStdIn in;
    public BinaryStdOut out;
    
    
    public String readBytesTransformToString(int x){
        String repr = "";
        for(int i=0; i<x; i++){
            if(in.isEmpty()){
                repr+="x";
                break;
            }
            boolean b = in.readBoolean();
            if(b){
                repr += "1";
            }else{
                repr += "0";
            }
            
        }
        return repr;
    }
    public DataTable readInput(int wordLength){
        DataTable data = new DataTable(wordLength);
        data.wordlen = wordLength;
        HashMap<String,Integer> map = new HashMap<>();
        data.ammountOf = 0;
        LinkedList<CodeTableEntry> list = new LinkedList<>();
        while(!in.isEmpty()){
            String rep = this.readBytesTransformToString(data.wordlen);
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
            entry.l = this.calculateL(entry.getProbability());
            list.add(entry);
        }
        Collections.sort(list);
        list = computeCodes(list);
        for(CodeTableEntry entry:list){
            data.map.put(entry.binaryRepresentation, entry);
        }
        data.list = list;
        return data;
        
    }
    
    public LinkedList<CodeTableEntry> computeCodes(LinkedList<CodeTableEntry> list){
        long currentAmmount = 0;
        
        for(CodeTableEntry entry:list){
            String code = Shannon.floatToBinaryString((double)currentAmmount/entry.dividedBy,entry.l+1);
            entry.codeAs = code.substring(0, entry.l);
            currentAmmount+=entry.ammount;
        }
        return list;
    }
    
    
    public void compress(DataTable data){
        int len =  data.map.size();
        out.write(len);// ammount of entries
        out.write(data.wordlen);//word length
        out.write(data.ammountOf); //amount of words
        out.write(data.leftOver.length());//ammount of uncompressed Raw bits
        this.writeStringAsBits(data.leftOver);
        
        
        for(CodeTableEntry entry:data.list){
            writeStringAsBits(entry.binaryRepresentation); // write variable word length code
            out.write(entry.ammount);// write ammount of that entry
        }
        for(String bin :data.sourceInBinary){
            CodeTableEntry entry = data.map.get(bin);
            writeStringAsBits(entry.codeAs);
        }
        
        out.flush();
        
    }
    
    public Task<Void> getCompressTask(DataTable data){
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                int len =  data.map.size();
                out.write(len);// ammount of entries
                out.write(data.wordlen);//word length
                out.write(data.ammountOf); //amount of words
                out.write(data.leftOver.length());//ammount of uncompressed Raw bits
                writeStringAsBits(data.leftOver);
                int progress = 1;
                int totalProgress = data.list.size() + data.sourceInBinary.size();

                for(CodeTableEntry entry:data.list){
                    writeStringAsBits(entry.binaryRepresentation); // write variable word length code
                    out.write(entry.ammount);// write ammount of that entry
                    progress++;
                    this.updateProgress(progress, totalProgress);
                }
                for(String bin :data.sourceInBinary){
                    CodeTableEntry entry = data.map.get(bin);
                    writeStringAsBits(entry.codeAs);
                    progress++;
                    this.updateProgress(progress, totalProgress);
                }

                out.flush();
                return null;
            }
        };
        return task;
    }
    public void writeStringAsBits(String str){
        for(char c :str.toCharArray()){
                if(c=='1'){
                    out.write(true);
                }else{
                    out.write(false);
                }
            }
    }
    
    public DataTable readInfoCompressed(){       
        LinkedList<CodeTableEntry> list = new LinkedList<>();
        int len = in.readInt();// ammount of entries
        int wordLen = in.readInt();//word length
        int ammount = in.readInt(); //amount of words
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
            int entryAmmount = in.readInt();
            CodeTableEntry entry = new CodeTableEntry(entryAmmount,ammount,word);
            entry.l = this.calculateL(entry.getProbability());
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
                int progress = 1;
                int totalProgress = data.ammountOf;
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

                        writeStringAsBits(map.get(word));
                        word = "";
                    }
                    progress++;
                    this.updateProgress(progress, totalProgress);
                }
                writeStringAsBits(data.leftOver);
                out.flush();
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
                
                this.writeStringAsBits(map.get(word));
                word = "";
            }
        }
        this.writeStringAsBits(data.leftOver);
        out.flush();
        
    }
    public static String floatToBinaryString( double n,int minLen ) {
        String val = "";    // Setting up string for result
        while ( n > 0 ) {     // While the fraction is greater than zero (not equal or less than zero)
            double r = n * 2;   // Multiply current fraction (n) by 2
            if( r >= 1 ) {      // If the ones-place digit >= 1
                val += "1";       // Concat a "1" to the end of the result string (val)
                n = r - 1;        // Remove the 1 from the current fraction (n)
            }else{              // If the ones-place digit == 0
                val += "0";       // Concat a "0" to the end of the result string (val)
                n = r;            // Set the current fraction (n) to the new fraction
            }
        }
        while(val.length()<minLen){
            val+="0";
        }
    return val;
    }   
    
    
    
    public int calculateL(double probability){
        return (int) Math.ceil(-1*(Math.log(probability) / Math.log(2)));
    }


    public Shannon(String fin,String fout) throws IOException{
        in = new BinaryStdIn(fin);
        out = new BinaryStdOut(fout);
    }
}
