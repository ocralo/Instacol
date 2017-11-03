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
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author rodrigoescobarlopez
 */
public class SeleccionarPerfilController implements Initializable {

    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private RadioButton rPerfil1;
    @FXML
    private RadioButton rPerfil2;
    @FXML
    private RadioButton rPerfil3;
    @FXML
    private Button botonSubir;
    @FXML
    private Button botonBajar;

    private String idBuscar;
    private LinkedList<Perfil> perfil;
    private LinkedList<Image> imagenes;
    private int contPerfil;
    private int triosImagenes;
    private int contadorImagenes;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contPerfil = 0;
        botonSubir.setVisible(false);
        botonBajar.setVisible(false);
        perfil = new LinkedList<>();
        imagenes = new LinkedList<>();
        contadorImagenes = 0;

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

                    idBuscar = auxDato[0];
                } catch (IOException ex) {
                    Logger.getLogger(CrearPerfilController.class.getName()).log(Level.SEVERE, null, ex);
                }
                perfil = objBases.buscarPerfil("cod_usuario", idBuscar);
//                System.out.println("En la consulta se cargaron " + perfil.size());
                for (int i = 0; i < perfil.size(); i++) {
//                    System.out.println("Imagen " + i);
                    Image imageB = SwingFXUtils.toFXImage((BufferedImage) perfil.get(i).getFoto_perfil(), null);
                    imagenes.add(imageB);
                }

                organizarImagenes();
                
                triosImagenes = (int) Math.ceil((double)imagenes.size()/3);
                
                if(triosImagenes > 1)
                {
                    botonBajar.setVisible(true);
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
    private void handleButtonActionBajar(ActionEvent event) throws IOException {
        if((contadorImagenes + 1) < triosImagenes)
        {
            contadorImagenes++;
        }
        
        organizarImagenes();
        
        if((contadorImagenes + 1) == triosImagenes)
        {
            botonBajar.setVisible(false);
        }
        
        if(contadorImagenes > 0)
        {
            botonSubir.setVisible(true);
        }
        
        System.out.println(contadorImagenes + " " + triosImagenes);
//        if () {
//
//        }
//        contPerfil += 3;
//        img1.setImage(imagenes.get(contPerfil));
//        rPerfil1.setText(perfil.get(contPerfil).getNombre_perfil());
//
//        img2.setImage(imagenes.get(contPerfil + 1));
//        rPerfil2.setText(perfil.get(contPerfil + 1).getNombre_perfil());
//
//        img3.setImage(imagenes.get(contPerfil + 2));
//        rPerfil3.setText(perfil.get(contPerfil + 2).getNombre_perfil());
//
//        if ((contPerfil - 3) == perfil.size()) {
//
//            botonBajar.setVisible(false);
//            botonBajar.setDisable(true);
//
//        }

    }

    @FXML
    private void handleButtonActionSubir(ActionEvent event) throws IOException {
        
        if(contadorImagenes > 0)
        {
            contadorImagenes--;
        }
        
        organizarImagenes();
        
        botonBajar.setVisible(true);
        
        if(contadorImagenes == 0)
        {
            botonSubir.setVisible(false);
        }
        
        System.out.println(contadorImagenes + " " + triosImagenes);
//        contPerfil -= 3;
//        img1.setImage(imagenes.get(contPerfil - 2));
//        rPerfil1.setText(perfil.get(contPerfil - 2).getNombre_perfil());
//
//        img2.setImage(imagenes.get(contPerfil - 1));
//        rPerfil2.setText(perfil.get(contPerfil - 1).getNombre_perfil());
//
//        img3.setImage(imagenes.get(contPerfil));
//        rPerfil3.setText(perfil.get(contPerfil).getNombre_perfil());
//
//        if (contPerfil == 0) {
//
//            botonSubir.setVisible(false);
//            botonSubir.setDisable(true);
//
//        }

    }

    private void organizarImagenes() {
        img2.setVisible(true);
        rPerfil2.setVisible(true);
        
        img3.setVisible(true);
        rPerfil3.setVisible(true);
        
        img1.setImage(imagenes.get(3*contadorImagenes));
        rPerfil1.setText(perfil.get(3*contadorImagenes).getNombre_perfil());

        if (perfil.size() > (3*contadorImagenes) + 1) {
            img2.setImage(imagenes.get(3*contadorImagenes + 1));
            rPerfil2.setText(perfil.get(3*contadorImagenes + 1).getNombre_perfil());
        } else {
            img2.setVisible(false);
            rPerfil2.setVisible(false);
        }

        if (perfil.size() > (3*contadorImagenes) + 2) {
            img3.setImage(imagenes.get(3*contadorImagenes + 2));
            rPerfil3.setText(perfil.get(3*contadorImagenes + 2).getNombre_perfil());
        } else {
            img3.setVisible(false);
            rPerfil3.setVisible(false);
        }
    }

}
