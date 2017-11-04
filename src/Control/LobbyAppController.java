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
import java.io.PrintWriter;
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
import javafx.scene.control.Button;
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
public class LobbyAppController implements Initializable {

    @FXML
    private ScrollPane scrollPaneImagenes;
    @FXML
    private AnchorPane anchorPaneImagenes;
    @FXML
    private ImageView iconoperfil;
    @FXML
    private ImageView imagenViewNews;
    @FXML
    private Label nombrePerfil;
    @FXML
    private Button botonAnteriorImagen;
    @FXML
    private Button botonSiguienteImagen;
    
    
    private String idUsuario,idBuscar;
    private LinkedList<Image> imagenesList;
    private LinkedList<imagen> imagenes;
    private int contador;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imagenesList = new LinkedList<>();
        imagenes = new LinkedList<>();
        contador = 0;
        botonAnteriorImagen.setVisible(false);
        
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
                    Logger.getLogger(LobbyAppController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    String aux = in.readLine();
                    String[] auxDato = aux.split(",");
                    idUsuario =auxDato[0];
                    idBuscar = auxDato[1];
                } catch (IOException ex) {
                    Logger.getLogger(LobbyAppController.class.getName()).log(Level.SEVERE, null, ex);
                }
            LinkedList<Perfil> perfil = objBases.buscarPerfil("id_perfil",idBuscar);
            System.out.println(perfil.size());
            Image imageB = SwingFXUtils.toFXImage((BufferedImage) perfil.get(0).getFoto_perfil(), null);
            
            iconoperfil.setImage(imageB);
            nombrePerfil.setText(perfil.get(0).getNombre_perfil());
            
            imagenes = objBases.buscarFoto();
                for (imagen imagen : imagenes) {
                    
                    Image image = SwingFXUtils.toFXImage((BufferedImage) imagen.getImagen(), null);
                    imagenesList.add(image);
                }
                if(imagenes.size() > 0)
                {
                    imagenViewNews.setImage(imagenesList.getFirst());
                }
        }catch (IOException ex) {
            Logger.getLogger(LobbyAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @FXML
    private void AgregarImagenNueva(ActionEvent event) throws IOException{
        Picr.changeScene("AgregarImagen.fxml", event);
    }
    
    @FXML
    private void abrirPerfil(MouseEvent event) throws IOException{
        Picr.changeScene("Perfil.fxml", event);
    }
    
    @FXML
    private void handleButtonActionSiguienteImagen(ActionEvent event) throws IOException{
        if(contador < (imagenesList.size()-1))
        {
            contador++;
        }
        
        if(contador == (imagenesList.size()-1))
        {
            botonSiguienteImagen.setVisible(false);
        }
        
        botonAnteriorImagen.setVisible(true);
        
        actualizarImagen();
    }
    
    @FXML
    private void handleButtonActionAnteriorImagen(ActionEvent event) throws IOException{
        if(contador > 0)
        {
            contador--;
        }
        if(contador == 0)
        {
            botonAnteriorImagen.setVisible(false);
        }
        
        botonSiguienteImagen.setVisible(true);
        
        actualizarImagen();
    }
    
    private void actualizarImagen() {
        Image image = imagenesList.get(contador);
        imagenViewNews.setImage(image);
    }
    
    @FXML
    private void AbrirImagen(MouseEvent event) throws IOException{
        PrintWriter writer = new PrintWriter("src/Imagenes/usuario.txt", "UTF-8");
        String txt = idUsuario + "," + idBuscar + ","+ imagenes.get(contador).getId_imagen();
        writer.println(txt);
        writer.close();
        Picr.changeScene("Fotos.fxml", event);
    }
    
}
