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
    private ImageView iconoPerfil;
    @FXML
    private ImageView imagenViewNews;
    @FXML
    private ImageView iconoPerfilNews;
    @FXML
    private Label nombrePerfilNews;
    @FXML
    private Label nombrePerfil;
    @FXML
    private Button botonAnteriorImagen;
    @FXML
    private Button botonSiguienteImagen;
    
    
    private String idUsuario,idBuscar;
    private LinkedList<imagen> imagenes;
    private LinkedList<Image> imagenesList;
    private LinkedList<Image> imagenPerfilesList;
    private LinkedList<String> nombrePerfilesList;
    private int contador;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imagenes = new LinkedList<>();
        imagenesList = new LinkedList<>();
        imagenPerfilesList = new LinkedList<>();
        nombrePerfilesList = new LinkedList<>();
        contador = 0;
        botonAnteriorImagen.setVisible(false);
        
        System.out.println("-------------------------------------");
        
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
            
            iconoPerfil.setImage(imageB);
            nombrePerfil.setText(perfil.get(0).getNombre_perfil());
            
            LinkedList<LinkedList<Object>> consulta = objBases.buscarImagenPerfil();
            
                for (LinkedList<Object> c : consulta) {
                    imagen i = ((imagen)c.getFirst());
                    Image imagen = SwingFXUtils.toFXImage((BufferedImage) i.getImagen(), null);
                    imagenesList.add(imagen);
                    imagenes.add(i);
                    
                    Perfil p = ((Perfil)c.getLast());
                    Image imagenPerfil = SwingFXUtils.toFXImage((BufferedImage) p.getFoto_perfil(), null);
                    imagenPerfilesList.add(imagenPerfil);
                    
                    nombrePerfilesList.add(p.getNombre_perfil());
                }
                if(consulta.size() > 0)
                {
                    imagenViewNews.setImage(imagenesList.getFirst());
                    iconoPerfilNews.setImage(imagenPerfilesList.getFirst());
                    nombrePerfilNews.setText(nombrePerfilesList.getFirst());
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
        Image fotoPerfil = imagenPerfilesList.get(contador);
        String nombrePerfil = nombrePerfilesList.get(contador);
        imagenViewNews.setImage(image);
        iconoPerfilNews.setImage(fotoPerfil);
        nombrePerfilNews.setText(nombrePerfil);
    }
    
    @FXML
    private void AbrirImagen(MouseEvent event) throws IOException{
        PrintWriter writer = new PrintWriter("src/Imagenes/usuario.txt", "UTF-8");
        String txt = idUsuario + "," + idBuscar + ","+ imagenes.get(contador).getId_imagen();
        writer.println(txt);
        writer.close();
        Picr.changeScene("Fotos.fxml", event);
    }
    
    @FXML
    private void handleButtonActionDesconectarse(ActionEvent event) throws IOException{
        Picr.changeScene("Inicio.fxml", event);
    }
}
