package Control;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Picr extends Application {

    /**
     * Permite el cambio de una escena a otra por medio de un FXML
     *
     * @param fxml - vista nueva a cargar
     * @param event - evento del mouse
     * @throws IOException - excepción relacionada con la lectura y escritura
     * del disco duro
     */
    public static void changeScene(String fxml, MouseEvent event) throws IOException {
        Parent homeParent = FXMLLoader.load(Picr.class.getResource("/Vista/" + fxml));
        Scene homeScene = new Scene(homeParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.hide();
        appStage.setScene(homeScene);
        appStage.show();
    }

    /**
     * Metodo que permite iniciar la ventana del palicativo
     *
     * @param stage - estado del app
     * @throws Exception - Exceptión generica
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/Inicio.fxml"));
        stage.setTitle("Picr");
        Image appIcon = new Image(getClass().getResourceAsStream("/Imagenes/imagenIcono.png"));
        stage.getIcons().add(appIcon);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Permite el cambio de una escena a otra por medio de un FXML
     *
     * @param fxml - vista nueva a cargar
     * @param event - evento del ActionEvent
     * @throws IOException - excepción relacionada con la lectura y escritura
     * del disco duro
     */
    public static void changeScene(String fxml, ActionEvent event) throws IOException {
        Parent homeParent = FXMLLoader.load(Picr.class.getResource("/Vista/" + fxml));
        Scene homeScene = new Scene(homeParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.hide();
        appStage.setScene(homeScene);
        appStage.show();
    }

    /**
     * Metodo que permite iniciar la aplicación
     *
     * @param args - Comando de la linea de argumentos
     */
    public static void main(String[] args) {
        launch(args);
    }

}
