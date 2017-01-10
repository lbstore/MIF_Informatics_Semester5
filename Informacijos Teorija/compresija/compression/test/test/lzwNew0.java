/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import lzwpack.CodeInputUnpacker;
import lzwpack.CodeOutputPacker;
import lzwpack.LZW;
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
public class lzwNew0 {
    
    public lzwNew0() {
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
    String input = "ori.txt";
    String temp = "t";
    String output = "Result";
    @Test
    public void test() throws IOException{
        InputStream in = Files.newInputStream(Paths.get(input));
        CodeOutputPacker out = new CodeOutputPacker(Files.newOutputStream(Paths.get(temp)));
        lzwpack.LZW.Compress(in, out);
    }
//    @Test
    public void test1() throws IOException{
        
        CodeInputUnpacker in = new CodeInputUnpacker(Files.newInputStream(Paths.get(temp)));
        OutputStream out = Files.newOutputStream(Paths.get(output));
        LZW.Expand(in, out);
    }
}
