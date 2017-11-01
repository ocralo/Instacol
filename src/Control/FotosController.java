/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DontTouch.Tools;
import Modelo.imagen;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author rodrigoescobarlopez
 */
public class FotosController implements Initializable {

    LinkedList<imagen> listaImagenes;
    Iterator<imagen> ite;

    @FXML
    private Label likeP;
    @FXML
    private Label likeS;
    @FXML
    private ImageView imagen1;
    @FXML
    private ImageView imagen2;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaImagenes = new LinkedList<>();
        String idPerfil;
        BaseDatos objBases = new BaseDatos();
        boolean conexion;
        conexion = objBases.crearConexion();
        if (conexion) {
            listaImagenes = objBases.buscarFoto();
            ite = listaImagenes.iterator();
            colocarImagenes();
        }
    }

    private void colocarImagenes() {
        if (ite.hasNext() && ite != null) {
            imagen1.setImage(SwingFXUtils.toFXImage((BufferedImage) ite.next().getImagen(), null));
        } else {
            Tools.imprimirC("No hay mas imagenes");
        }

        if (ite.hasNext() && ite != null) {
            imagen2.setImage(SwingFXUtils.toFXImage((BufferedImage) ite.next().getImagen(), null));
        } else {
            Tools.imprimirC("No hay mas imagenes");
        }
    }

    @FXML
    private void likePrimero(ActionEvent event) {

    }

    @FXML
    private void likeSegundo(ActionEvent event) {

    }
}
