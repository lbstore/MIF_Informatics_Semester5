/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import compression.LZWCompression;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lemmin
 */
public class t3 {
    
    public t3() {
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
    public String input = "t.exe";
    public String output = "fileOut";
    public int size = 4050;
//    @Test
    public void test1() throws IOException{
        LZWCompression comp = new LZWCompression(size);
        comp.LZW_Compress(input, "temp");
        System.out.println("Compressed");
    }
    @Test
    public void test2() throws IOException {
        LZWCompression comp = new LZWCompression(size);
        comp.LZW_Decompress("temp", output);
        System.out.println("Expanded");
    }
}
