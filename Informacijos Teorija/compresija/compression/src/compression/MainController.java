/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compression;

import compression.Shannon.DataTable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Lemmin
 */
public class MainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML public TextField inputField;
    @FXML public TextField outputField;
    @FXML public TextField argumentShannon;
    @FXML public TextField argumentLZW;
    @FXML public TabPane tabs;
    @FXML public ProgressBar progressBar;
    @FXML public Button btnExp;
    @FXML public Button btnComp;
    @FXML public Label status;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void beforeShow(){
        progressBar.setVisible(false);
    }
    public boolean checkLZW(){
        int size = 0;
        try{
            size = Integer.parseInt(argumentLZW.getText().trim());
        }catch(Exception e){}
        return !(size>4096 || size<256);
    }
    public boolean checkShannon(){
        int size = 0;
        try{
            size = Integer.parseInt(argumentShannon.getText().trim());
        }catch(Exception e){}
        return !(size>20 || size<1);
    }
    public void compress() throws IOException{
        System.out.println("Mode:"+tabs.getSelectionModel().getSelectedIndex());
        int mode = tabs.getSelectionModel().getSelectedIndex();
        try{
            if(mode == 0){
                if(!checkShannon()){
                    status.setText("Bad Shannon argument");
                    return;
                }
                //Shannon
                int size = Integer.parseInt(argumentShannon.getText().trim());
                Shannon comp = new Shannon(inputField.getText().trim(),outputField.getText().trim());


                Shannon.DataTable data = comp.readInput(size);
                comp.compress(data);
                status.setText("Shannon compression done");
//                Task<Void> task = comp.getCompressTask(data);
//                progressBar.progressProperty().bind(task.progressProperty());
//                task.setOnSucceeded(e->{
//                    status.setText("Shannon compression done");
//                });
//                new Thread(task).start();

            }else if(mode == 1){
                if(!checkLZW()){
                    status.setText("Bad LZW argument");
                    return;
                }
                //LZW
                int size = Integer.parseInt(argumentLZW.getText().trim());
                LZWCompression comp = new LZWCompression(size);
                comp.LZW_Compress(inputField.getText().trim(),outputField.getText().trim());
                status.setText("LZW compression done");
            }
        }catch(Exception e){
            status.setText(e.toString());
        }
        
    }
    
    public void expand() throws IOException{
        
        System.out.println("Mode:"+tabs.getSelectionModel().getSelectedIndex());
        int mode = tabs.getSelectionModel().getSelectedIndex();
        try{
            if(mode == 0){
                //Shannon
                Shannon comp = new Shannon(inputField.getText().trim(),outputField.getText().trim());
                DataTable data = comp.readInfoCompressed();
                comp.expand(data);
//                Task<Void> task = comp.getExpandTask(data);
//                progressBar.progressProperty().bind(task.progressProperty());
//                task.setOnSucceeded(e->{
//                    status.setText("Shannon expansion done");
//                });
//                
//                new Thread(task).start();
                status.setText("Shannon expansion done");
            }else if(mode ==1){
                //LZW
                int size = Integer.parseInt(argumentLZW.getText().trim());
                LZWCompression comp = new LZWCompression(size);
                comp.LZW_Decompress(inputField.getText().trim(),outputField.getText().trim());
                btnExp.setDisable(false);
                btnComp.setDisable(false);
                status.setText("LZW expansion done");

            }
        }catch(Exception e){
            status.setText(e.toString());
        }
        
        
    }
    
    
    
}
