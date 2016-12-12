/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import compression.Shannon;
import compression.Shannon.DataTable;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 *
 * @author Lemmin
 */
public class t2 {
    
    public t2() {
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
    @Test
    public void test() throws IOException{
        {
            Shannon sh = new Shannon("file.txt","t");
            DataTable data = sh.readInput(1);
            sh.compress(data);
        }
        
        System.out.println("Compressed");
        {
            Shannon sh = new Shannon("t","fileOut");
            DataTable data = sh.readInfoCompressed();
            sh.expand(data);
        }
        System.out.println("Decompressed");
        
    }
    
    public void test2() throws IOException{
        Shannon sh = new Shannon("file1.txt","file2.txt");
        DataTable data = sh.readInfoCompressed();
        sh.expand(data);
    }
}
