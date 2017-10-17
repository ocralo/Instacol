/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javax.swing.JOptionPane;

/**
 *
 * @author rodrigoescobarlopez
 */
public class InicioController implements Initializable {
    
   
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            BaseDatos objBases = new BaseDatos();
            boolean conexion;
            conexion = objBases.crearConexion();
        if (conexion) {
            System.out.println("se hizo conexion");
        } else {
            JOptionPane.showMessageDialog(null,"no se pudo realizar la conexión");
        }
    }
    
}
