package Control;

import Modelo.usuario;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Momo
 */
public class RegistrarController implements Initializable {

    @FXML private TextField TFnombre,TFapellido,TFcorreo;
    @FXML private PasswordField PFclave;
    @FXML private DatePicker DPnacimiento;
    
    usuario objUsuario;
    ArrayList<usuario> arrUsuario;
    LocalDate localdate;
    
    @FXML
    private void registrarUsuario(ActionEvent event) throws IOException {
        String idusuario = "";
        String correo;
        String nombre;
        String clave;
        String apellido;
        String nacimiento;
        
        nombre = TFnombre.getText();
        apellido = TFapellido.getText();
        correo = TFcorreo.getText();
        clave = PFclave.getText();
        localdate = DPnacimiento.getValue();
        nacimiento = localdate.toString();
        
        objUsuario = new usuario(nombre, apellido, correo, clave, nacimiento, idusuario);
        arrUsuario.add(objUsuario);
        
        boolean insertar = false;

        insertar = objUsuario.insertarUsuario(arrUsuario);

        if (insertar) {
            System.out.println("Se han insertado los usuarios correctamente");
        } else { 
            System.out.println("No se pudo insertar adecuadamente");
        }
        arrUsuario.clear();
        Picr.changeScene("CrearPerfil.fxml", event);
    }
    
    @FXML
    private void cancelar(ActionEvent event) throws IOException {
       Picr.changeScene("Inicio.fxml", event);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DPnacimiento.setValue(LocalDate.of(1996,1,1));
        BaseDatos objBases = new BaseDatos();
        boolean conexion;
            conexion = objBases.crearConexion();
            if (conexion) {
            
            } else {
            System.out.println("no se pudo realizar la conexión");
            }
        objUsuario = new usuario();
        arrUsuario = new ArrayList<>();
    }    
    
}
