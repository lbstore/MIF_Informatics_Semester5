/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compression;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Lemmin
 */
public class Compression extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        URL url = getClass().getResource("main.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Compression LZW/Shannon");
        stage.setScene(new Scene(root));
        MainController controller = (MainController) loader.getController();
        controller.beforeShow();
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
