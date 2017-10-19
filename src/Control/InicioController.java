/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;


import javafx.scene.control.TextField;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;

/**
 *
 * @author rodrigoescobarlopez
 */
public class InicioController implements Initializable {
    
    @FXML private ImageView Logo;
    @FXML private TextField TFcorreo;
    @FXML private PasswordField PFclave;
    
    private boolean correcto = false;
    ArrayList arr;
    
    
    @FXML
    private void InicioSesion(ActionEvent event) throws IOException {
        String buscar,asegurar,asegurar2,idUsuario,correoU;
        buscar = TFcorreo.getText();
        asegurar = PFclave.getText();
        boolean conexion;
        BaseDatos objbases = new BaseDatos();
        try {
            conexion = objbases.crearConexion();
            if (conexion) {
                arr = objbases.buscarCorreo(buscar);
                
                asegurar2 = arr.get(3).toString();
                idUsuario = arr.get(5).toString();
                correoU = arr.get(2).toString();
                
                if(asegurar.equals(asegurar2)){
                correcto=true;
                }
                 if(correcto == true){
                     PrintWriter writer = new PrintWriter("src/Imagenes/usuario.txt","UTF-8");
                     writer.println(idUsuario+","+correoU);
                     writer.close();
                      Picr.changeScene("IniciarSesion.fxml", event);
                        }else{
                         System.out.println("Usuario o contraseña incorrecta");
                        }
            }
        }catch (IOException ex) {
            Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            System.out.println("No se pudo realizar la conexión");
        }
    }
    
}
