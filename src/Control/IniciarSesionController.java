/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Perfil;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Momo
 */
public class IniciarSesionController implements Initializable {

    @FXML
    private ScrollPane scrollPaneImagenes;
    @FXML
    private AnchorPane anchorPaneImagenes;
    @FXML
    private ImageView iconoperfil;
    @FXML
    private Label nombrePerfil;
    
     private String idBuscar;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BaseDatos objBases = new BaseDatos();
        boolean conexion;
        conexion = objBases.crearConexion();
        if (conexion) {

        } else {
            System.out.println("no se pudo realizar la conexión");
        }
        try {
            BufferedReader in = null;
                try {
                    in = new BufferedReader(new FileReader("src/Imagenes/usuario.txt"));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CrearPerfilController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    String aux = in.readLine();
                    String[] auxDato = aux.split(",");

                    idBuscar = auxDato[1];
                } catch (IOException ex) {
                    Logger.getLogger(CrearPerfilController.class.getName()).log(Level.SEVERE, null, ex);
                }
            LinkedList<Perfil> perfil = objBases.buscarPerfil("id_perfil",idBuscar);
            System.out.println(perfil.size());
            Image imageB = SwingFXUtils.toFXImage((BufferedImage) perfil.get(0).getFoto_perfil(), null);
            
            iconoperfil.setImage(imageB);
            nombrePerfil.setText(perfil.get(0).getNombre_perfil());
        }catch (IOException ex) {
            Logger.getLogger(PERFILController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @FXML
    private void AgregarImagenNueva(ActionEvent event) throws IOException{
        Picr.changeScene("AgregarImagen.fxml", event);
    }
    
    @FXML
    private void abrirPerfil(MouseEvent event) throws IOException{
        Picr.changeScene("PERFIL.fxml", event);
    }
    
}
