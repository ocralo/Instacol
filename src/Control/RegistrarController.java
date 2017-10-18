package Control;

import Modelo.usuario;
import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Momo
 */
public class RegistrarController implements Initializable {

    usuario objUsuario;
    ArrayList<usuario> arrUsuario;
    
  
    
    @FXML
    private void registrar(ActionEvent event) throws IOException {
        String idusuario = "";
        String correo;
        String nombre;
        String clave;
        String apellido;
        String nacimiento;
        
//        nombre = TFnombre.getText();
//        apellido = TFapellido.getText();
//        correo = TFcorreo.getText();
//        clave = PFclave.getText();
//        apellido = DPnacimiento.getText();
        
//        objUsuario = new usuario(nombre, apellido, correo, clave, nacimiento, idusuario);
        arrUsuario.add(objUsuario);
        
        boolean insertar = false;

        insertar = objUsuario.insertarUsuario(arrUsuario);

        if (insertar) {
            JOptionPane.showMessageDialog(null,"Se han insertado los usuarios correctamente");
        } else {
            JOptionPane.showMessageDialog(null,"No se pudo insertar adecuadamente");
        }
        arrUsuario.clear();
        
    }
    
    @FXML
    private void cancelar(ActionEvent event) throws IOException {
       Picr.changeScene("Inicio.fxml", event);
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
        objUsuario = new usuario();
        arrUsuario = new ArrayList<>();
    }    
    
}
