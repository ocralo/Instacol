/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author rodrigoescobarlopez
 */
public class SeleccionarPerfilController implements Initializable {
    
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private RadioButton rPerfil1;
    @FXML
    private RadioButton rPerfil2;
    @FXML
    private RadioButton rPerfil3;
    @FXML
    private Button subir;
    @FXML
    private Button bajar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        subir.setVisible(false);
        subir.setDisable(false);
    }    
    
    @FXML
    private void dfg(ActionEvent event) throws IOException {
    
        
    
    }
    
}
