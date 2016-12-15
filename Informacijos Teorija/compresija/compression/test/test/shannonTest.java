/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import compression.Shannon;
import java.io.IOException;
import javafx.concurrent.Task;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Lemmin
 */
public class shannonTest {
    
    public shannonTest() {
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
    String fileIn = "q.pdf";
    String fileOut = "fileOut";
    String fileTemp = "t";
    int word = 30;
    @Test
    public void hello() throws IOException, InterruptedException {
                Shannon comp = new Shannon(fileIn,fileTemp);


                Shannon.DataTable data = comp.readInput(word);
                comp.compress(data);
                Task<Void> task = comp.getCompressTask(data);
                task.setOnSucceeded(e->{
                    System.out.println("Shannon compression done");
                });
//                task.run();
//                comp.out.close();
                System.out.println("DONE");

    }
}
