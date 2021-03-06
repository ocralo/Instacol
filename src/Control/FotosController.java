/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author rodrigoescobarlopez
 */
public class FotosController implements Initializable {

    LinkedList<Image> imagenesList;
    LinkedList<imagen> imagenes;
    LinkedList<LinkedList<String>> comentarios;
    String reporteComentarios;
    BaseDatos objBases;
    private String idImagen,idBuscar;
    private int contador;

    @FXML
    private ToggleButton ToggleButtonLike;
    @FXML
    private Button boton;
    @FXML
    private Label like;
    @FXML
    private Label LabelPerfil;
    @FXML
    private ImageView imagenViewImagenes;
    @FXML
    private ImageView imagenViewPerfil; 
    @FXML
    private TextArea textAreaComentarios; 
    @FXML
    private TextArea textAreaEnviarComentario;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contador = 0;
        reporteComentarios = "";
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
                
                actualizarComentarios();
                
                LinkedList<Perfil> perfil = objBases.buscarPerfil("id_perfil",idBuscar);
                System.out.println(perfil.size());
                Image imageB = SwingFXUtils.toFXImage((BufferedImage) perfil.get(0).getFoto_perfil(), null);

                imagenViewPerfil.setImage(imageB);
                LabelPerfil.setText(perfil.get(0).getNombre_perfil());
                like.setText(String.valueOf(imagenes.getFirst().getMe_gusta()));
                if(objBases.buscarPerfilImagen(idBuscar).isEmpty()){
                    ToggleButtonLike.setSelected(false);
                }else{
                    ToggleButtonLike.setSelected(true);
                }
                
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
       String choice = "a";
       if(ToggleButtonLike.isSelected()){ choice = "b";}
       
       switch(choice){
       
            case "b":
               objBases.CrearLike(String.valueOf(0),idBuscar, idImagen);
               break;
            case "a":
                objBases.EliminarLike(idBuscar);
                break;
        }
       actualizarLikesLabel();
   }
   
   @FXML
    private void handleButtonActionEnviarComentario(ActionEvent event) throws IOException{
        String comentario = textAreaEnviarComentario.getText().trim();
        
        if(!comentario.equals(""))
        {
            objBases.insertarComentario(idBuscar, idImagen, comentario);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Ingrese un comentario");
        }
        
        actualizarComentarios();
        
        textAreaEnviarComentario.setText("");
    }
    
    private void actualizarLikesLabel() throws IOException{
        int megusta =objBases.NumeroLikes();
        objBases.UpdateLike(String.valueOf(megusta), idImagen);
        imagenes = objBases.buscarImagen("id_imagen", idImagen);
        like.setText(imagenes.getFirst().getMe_gusta());
    }
    
    private void actualizarComentarios() {
        reporteComentarios = "";
        comentarios = objBases.buscarComentariosPerfil(idImagen);
        for (LinkedList<String> comentario : comentarios) {
            reporteComentarios += comentario.getFirst() + ": " + comentario.getLast() + "\n";
        }

        textAreaComentarios.setText(reporteComentarios);
    }
    
}
