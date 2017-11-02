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
    imagen img1;
    imagen img2;
    int ite1;
    int ite2;

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
        if (objBases.crearConexion()) {
            listaImagenes = objBases.buscarFoto();
            ite1 = 0;
            ite2 = 1;
            colocarImagenes();
        }
    }

    private void colocarImagenes() {
        imagen1.setImage(null);
        imagen2.setImage(null);
        img1 = null;
        img2 = null;
        likeP.setText("0");
        likeS.setText("0");

        if (ite1 < listaImagenes.size()) {
            img1 = listaImagenes.get(ite1);
            imagen1.setImage(SwingFXUtils.toFXImage((BufferedImage) img1.getImagen(), null));
            likeP.setText(img1.getMe_gusta());
        } else {
            Tools.imprimirC("No hay mas imagenes");
        }

        if (ite2 < listaImagenes.size()) {
            img2 = listaImagenes.get(ite2);
            imagen2.setImage(SwingFXUtils.toFXImage((BufferedImage) img2.getImagen(), null));
            likeS.setText(img2.getMe_gusta());
        } else {
            Tools.imprimirC("No hay mas imagenes");
        }
    }

    @FXML
    private void likePrimero(ActionEvent event) {
        if (img1 != null) {
            BaseDatos objBaseDatos = new BaseDatos();
            if (objBaseDatos.crearConexion()) {
                img1.setMe_gusta((Integer.parseInt(img1.getMe_gusta()) + 1) + "");
                objBaseDatos.agregarLike(img1);
            }
            colocarImagenes();
        }
    }

    @FXML
    private void likeSegundo(ActionEvent event) {

        if (img2 != null) {
            BaseDatos objBaseDatos = new BaseDatos();
            if (objBaseDatos.crearConexion()) {
                img2.setMe_gusta((Integer.parseInt(img2.getMe_gusta()) + 1) + "");
                objBaseDatos.agregarLike(img2);
            }
            colocarImagenes();
        }
    }

    @FXML
    private void botonAbajo(ActionEvent event) {
        if (ite2 < listaImagenes.size()) {
            ite1 += 2;
            ite2 += 2;
        }
        colocarImagenes();
    }

    @FXML
    private void botonArriba(ActionEvent event) {
        if (ite1 > 0) {
            ite1 -= 2;
            ite2 -= 2;
        }
        colocarImagenes();
    }
}
