/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shannonunlimited;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author Laimonas Beniušis
 */
public class FileReader {
    public static Collection<String> readFromFile(String URL) throws UnsupportedEncodingException, FileNotFoundException, IOException{
       LinkedList<String> list = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(URL),"UTF-8"))) {
            reader.lines().forEach((String line) ->{
                list.add(line);
            });
        }
       return list;
    }
    public static Collection<String> readFromFile(String URL,String lineComment, String commentStart, String commentEnd) throws FileNotFoundException, IOException {
        LinkedList<String> list = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(URL),"UTF-8"))) {
            reader.lines().forEach((String ln) -> {
                int indexOf = ln.indexOf(lineComment);      //Find comment start
                if(indexOf!=0){
                    String line;
                    if(indexOf==-1){
                        line = ln;
                    }else{
                        line = ln.substring(0,indexOf);
                    }
                    list.add(line);
                }
                
            });
        }
        boolean comment = false;
        for(int i = 0; i<list.size(); i++){
            String str = list.get(i);
            int indexOfCommentStart = str.indexOf(commentStart);
            int indexOfCommentEnd   = str.indexOf(commentEnd);
            boolean foundCommentStart   = indexOfCommentStart  > -1;
            boolean foundCommentEnd     = indexOfCommentEnd > -1;
            boolean remove = true;
            if(foundCommentStart && foundCommentEnd){
                str = str.replace(str.substring(indexOfCommentStart, indexOfCommentEnd+2),"");
                remove = false;
            }else if(foundCommentStart){
                comment = true;
                remove = false;
                str = str.substring(0, indexOfCommentStart);
            }else if(foundCommentEnd){
                if(comment){
                    remove = false;
                    str = str.substring(indexOfCommentEnd+2,str.length());
                    if(str.isEmpty()){
                       remove = true;
                    }
                    comment = false;
                }
            }else{
                remove = false;
            }       
            if(remove){
                list.remove(i--);
            }else{
                list.set(i, str);
            }
            
        }
        return list;
    }
    public static void writeToFile(String URL,Collection<String> list) throws FileNotFoundException, UnsupportedEncodingException{
        PrintWriter out = new PrintWriter(URL, "UTF-8");
        list.forEach(line ->{
            out.println(line);
        });
        out.close();
    }
}
