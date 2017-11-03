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
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author rodrigoescobarlopez
 */
public class PERFILController implements Initializable {

    @FXML
    private ImageView imagenPerfil;
    @FXML
    private Label nombrePerfil;

    private String idBuscar;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        BaseDatos objBases = new BaseDatos();
        boolean conexion;
        conexion = objBases.crearConexion();
        if (conexion) {
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
                if(perfil.size() > 0)
                {
                    Image imageB = SwingFXUtils.toFXImage((BufferedImage) perfil.get(0).getFoto_perfil(), null);
                    imagenPerfil.setImage(imageB);
                    nombrePerfil.setText(perfil.get(0).getNombre_perfil()); 
                }
                

                

            } catch (IOException ex) {
                Logger.getLogger(PERFILController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!idBuscar.isEmpty()) {

            }

        } else {
            System.out.println("No se pudo realizar la conexión");
        }
    }

    
    @FXML
    private void CambiarPerfil(ActionEvent event) throws IOException {
        Picr.changeScene("SeleccionarPerfil.fxml", event);
    }

}
