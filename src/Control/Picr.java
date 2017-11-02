/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author rodrigoescobarlopez
 */
public class Picr extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/IniciarSesion.fxml"));
        stage.setTitle("Picr");
        Image appIcon = new Image(getClass().getResourceAsStream("/Imagenes/imagenIcono.png"));
        stage.getIcons().add(appIcon);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

   public static void changeScene(String fxml, ActionEvent event) throws IOException {
        
        Parent homeParent = FXMLLoader.load(Picr.class.getResource("/Vista/" + fxml));
        Scene homeScene = new Scene(homeParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.hide();
        appStage.setScene(homeScene);
        appStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
