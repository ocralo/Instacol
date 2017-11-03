/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Perfil;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author rodrigoescobarlopez
 */
public class CrearPerfilController implements Initializable {
    
     @FXML
    private ImageView fotoPerfil;
     @FXML
    private TextField nombrePTex;
     
    private String srcimg;
    private Image image;
    private File file;
    
    private String idUsuario;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       BufferedReader in = null;
         try {
             in = new BufferedReader(new FileReader("src/Imagenes/usuario.txt"));
         } catch (FileNotFoundException ex) {
             Logger.getLogger(CrearPerfilController.class.getName()).log(Level.SEVERE, null, ex);
         }
         try {
             String aux=in.readLine();
             String[] auxDato = aux.split(",");
             idUsuario = auxDato[0];
         } catch (IOException ex) {
             Logger.getLogger(CrearPerfilController.class.getName()).log(Level.SEVERE, null, ex);
         }
                
        BaseDatos objBases = new BaseDatos();
        boolean conexion;
        conexion = objBases.crearConexion();
        if (conexion) {

        } else {
            System.out.println("No se pudo realizar la conexión");
        }
    }
    
    
    @FXML
    private void fotoPerfil(ActionEvent event) throws IOException {
        
        Stage st = new Stage(StageStyle.UTILITY);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Archivos JPG");
        //filtro para solo traer imagenes
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg"));
        //trae la ruta del archivo
        file = fileChooser.showOpenDialog(st);
        image = new Image(file.toURI().toString());

        fotoPerfil.setImage(image);
        srcimg = file.getAbsolutePath();
    }
    
    @FXML
    private void subirPerfil(ActionEvent event) throws IOException, SQLException {
        BaseDatos objBases = new BaseDatos();
        boolean conexion;
        conexion = objBases.crearConexion();
        if (conexion) {
        String nombre = nombrePTex.getText();
        BufferedImage imageB = SwingFXUtils.fromFXImage(image, null);
        String codUsuario= idUsuario;//cambiar
        Perfil perfil = new Perfil(nombre, imageB, codUsuario);
        
        objBases.insertarPerfil(perfil, srcimg);
        objBases.pedirUsuario(idUsuario);
        
        
        Picr.changeScene("PERFIL.fxml", event);
        }else {
            System.out.println("No se pudo realizar la conexión");
        }
    }

}