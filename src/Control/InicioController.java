/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import sun.security.util.Password;

/**
 *
 * @author rodrigoescobarlopez
 */
public class InicioController implements Initializable {
    
    @FXML
    private ImageView Logo;
    
    
    @FXML
    private void InicioSesion(ActionEvent event) throws IOException {
       
    }
    
    @FXML
    private void Registrar(ActionEvent event) throws IOException {
       Picr.changeScene("Registrar.fxml", event);
    }

   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BaseDatos objBases = new BaseDatos();
        boolean conexion;
            conexion = objBases.crearConexion();
            if (conexion) {
            
            } else {
            JOptionPane.showInputDialog("no se pudo realizar la conexión");
            }
    }
    
}
