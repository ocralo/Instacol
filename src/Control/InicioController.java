/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Usuario;
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
import javax.swing.JOptionPane;

/**
 *
 * @author rodrigoescobarlopez
 */
public class InicioController implements Initializable {

    @FXML
    private ImageView Logo;
    @FXML
    private TextField TFcorreo;
    @FXML
    private PasswordField PFclave;

    private boolean correcto = false;
    ArrayList arr;

    @FXML
    private void InicioSesion(ActionEvent event) throws IOException {
        String buscar, clave, verifClave, idUsuario, correoU;
        buscar = TFcorreo.getText();
        clave = PFclave.getText();
        boolean conexion;
        BaseDatos objbases = new BaseDatos();
        try {
            conexion = objbases.crearConexion();
            if (conexion) {
                arr = objbases.buscarCorreo(buscar);

                if (arr.size() > 0) {
                    verifClave = arr.get(Usuario.CLAVE).toString();
                    idUsuario = arr.get(Usuario.ID).toString();
                    correoU = arr.get(Usuario.CORREO).toString();

                    if (clave.equals(verifClave)) {
                        correcto = true;
                    }
                    if (correcto) {
                        PrintWriter writer = new PrintWriter("src/Imagenes/usuario.txt", "UTF-8");
                        String User = idUsuario;
                        writer.println(User);
                        writer.close();
//                        Picr.changeScene("IniciarSesion.fxml", event);
                        Picr.changeScene("SeleccionarPerfil.fxml", event);
                    } else {
                        System.out.println("Usuario o contraseña incorrecta");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontraron los datos ingresados");
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Registrar(ActionEvent event) throws IOException {
        Picr.changeScene("Registrar.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BaseDatos db = new BaseDatos();
        boolean conexion = db.crearConexion();
        if (conexion) {

        } else {
            System.out.println("No se pudo realizar la conexión");
        }
    }

}
