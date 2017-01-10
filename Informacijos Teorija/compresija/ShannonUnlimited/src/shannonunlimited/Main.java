/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shannonunlimited;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Lemmin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
        ArrayList<String> info = new ArrayList(FileReader.readFromFile("parameters.txt", "#", "/*", "*/"));
        String fileIn = info.get(0);
        String fileOut = info.get(1);
        ShannonUnlimited comp = new ShannonUnlimited(fileIn,fileOut);
        if (info.size() == 3){
            int len = Integer.parseInt(info.get(2));
            if (len<2 || len>16){
                throw new Exception("Word length should be 2-16");
            }
            comp.compress(comp.analyze(len));
        }else{
            comp.expand();
        }
        
        
    }
    
}
