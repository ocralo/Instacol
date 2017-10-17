/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.perfil;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;

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

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String idBuscar = "2";
        BaseDatos objBases = new BaseDatos();
        boolean conexion;
        conexion = objBases.crearConexion();
        if (conexion) {
            try {
                LinkedList<perfil> perfil = objBases.buscarPerfil(idBuscar);
                
                
                Image imageB = SwingFXUtils.toFXImage((BufferedImage) perfil.get(0).getFoto_perfil(), null);
                
                imagenPerfil.setPreserveRatio(true);
                imagenPerfil.setImage(imageB);
                nombrePerfil.setText(perfil.get(0).getNombre_perfil());
            } catch (IOException ex) {
                Logger.getLogger(PERFILController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!idBuscar.isEmpty()) {
                
                

            }

        } else {
            JOptionPane.showMessageDialog(null, "no se pudo realizar la conexión");
        }
    }

    public String traerNombrePerfil(String idBuscar) {

        return "";
    }

}
