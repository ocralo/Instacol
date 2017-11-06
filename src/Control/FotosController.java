/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.MeGustaImagen;
import Modelo.Perfil;
import Modelo.imagen;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author rodrigoescobarlopez
 */
public class FotosController implements Initializable {

    LinkedList<Image> imagenesList;
    LinkedList<imagen> imagenes;
    BaseDatos objBases;
    private String idImagen,idBuscar;
    private int contador;

    @FXML
    private ToggleButton ToggleButtonLike;
    @FXML
    private Label like;
    @FXML
    private Label LabelPerfil;
    @FXML
    private ImageView imagenViewImagenes;
    @FXML
    private ImageView imagenViewPerfil; 

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contador = 0;
        imagenesList = new LinkedList<>();
        imagenes = new LinkedList<>();
        objBases = new BaseDatos();
        boolean conexion;
        conexion = objBases.crearConexion();
        if (conexion) {
            try {
                BufferedReader in = null;
                try {
                    in = new BufferedReader(new FileReader("src/Imagenes/usuario.txt"));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FotosController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    String aux = in.readLine();
                    String[] auxDato = aux.split(",");
                    idBuscar = auxDato[1];
                    idImagen = auxDato[2];
                } catch (IOException ex) {
                    Logger.getLogger(FotosController.class.getName()).log(Level.SEVERE, null, ex);
                }
                imagenes = objBases.buscarImagen("id_imagen", idImagen);
                for (imagen imagen : imagenes) {
                    Image image = SwingFXUtils.toFXImage((BufferedImage) imagen.getImagen(), null);
                    imagenesList.add(image);
                }
                if(imagenes.size() > 0)
                {
                    imagenViewImagenes.setImage(imagenesList.getFirst());
                }
                LinkedList<Perfil> perfil = objBases.buscarPerfil("id_perfil",idBuscar);
                System.out.println(perfil.size());
                Image imageB = SwingFXUtils.toFXImage((BufferedImage) perfil.get(0).getFoto_perfil(), null);

                imagenViewPerfil.setImage(imageB);
                LabelPerfil.setText(perfil.get(0).getNombre_perfil());
                like.setText(String.valueOf(objBases.buscarLikes()));
                
            }catch (IOException ex) {
                Logger.getLogger(FotosController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else {
            System.out.println("No se pudo realizar la conexión");
        }
    }

    @FXML
    private void handleButtonActionAtras(ActionEvent event) throws IOException{
        Picr.changeScene("Perfil.fxml", event);
    }
    
   @FXML
   private void DarLike(ActionEvent event) throws IOException{
       if(ToggleButtonLike.isSelected()){
           contador++;
       }else{
           contador--;
       }
       
       MeGustaImagen megusta = new MeGustaImagen(String.valueOf(0), String.valueOf(0), img.getLast().getId_imagen());
       objBases.InsertLike(megusta);
       actualizarLikesLabel();
   }
    
    private void actualizarLikesLabel() throws IOException{
        objBases.ActualizarLikes(String.valueOf(contador), idImagen);
        like.setText(String.valueOf(objBases.buscarLikes()));
    }
    
}
