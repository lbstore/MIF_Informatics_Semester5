/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import compression.ShannonUnlimited;
import java.io.IOException;
import java.math.BigInteger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Lemmin
 */
public class shannonUpdate0 {
    
    public shannonUpdate0() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    String fileIn = "ori.txt";
    String fileOut = "fileOut";
    String fileTemp = "t";
    int word = 8;
//    @Test
    public void test1() throws IOException{
        ShannonUnlimited sh = new ShannonUnlimited(fileIn,fileTemp);
        ShannonUnlimited.DataTable table = sh.analyze(word);
        sh.compress(table);
        
        System.out.println("Compressed");
        System.out.println(ShannonUnlimited.calculateL(BigInteger.valueOf(12), BigInteger.valueOf(1200)));
    }
    @Test
    public void test2() throws IOException{
        ShannonUnlimited sh = new ShannonUnlimited(fileTemp,fileOut);
        sh.expand();
        System.out.println("Decompressed");

        
    }
}
