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
import java.io.File;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author rodrigoescobarlopez
 */
public class PerfilController implements Initializable {

    @FXML
    private ImageView imagenPerfil;
    @FXML
    private ImageView imagenViewImagenes;
    @FXML
    private Label nombrePerfil;
    @FXML
    private Button botonAnteriorImagen;
    @FXML
    private Button botonSiguienteImagen;

    private String idBuscar,idUsuario;
    private LinkedList<Image> imagenesList;
    private LinkedList<imagen> imagenes;
    private int contador;
    private BaseDatos objBases;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        imagenesList = new LinkedList<>();
        imagenes = new LinkedList<>();
        contador = 0;
        
        
        objBases = new BaseDatos();
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
                    idUsuario = auxDato[0];
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
                imagenes = objBases.buscarImagen("cod_perfil_imagen", idBuscar);
                for (imagen imagen : imagenes) {
                    
                    Image image = SwingFXUtils.toFXImage((BufferedImage) imagen.getImagen(), null);
                    imagenesList.add(image);
                }
                if(imagenes.size() > 0)
                {
                    imagenViewImagenes.setImage(imagenesList.getFirst());
                }
                
                
            } catch (IOException ex) {
                Logger.getLogger(PerfilController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!idBuscar.isEmpty()) {

            }

        } else {
            System.out.println("No se pudo realizar la conexión");
        }
        
        botonAnteriorImagen.setVisible(false);
        if(imagenesList.size() == 1)
        {
            botonSiguienteImagen.setVisible(false);
        }
    }

    
    @FXML
    private void CambiarPerfil(ActionEvent event) throws IOException {
        Picr.changeScene("SeleccionarPerfil.fxml", event);
    }
    
    @FXML
    private void CrearNuevoPerfil(ActionEvent event) throws IOException{
        Picr.changeScene("CrearPerfil.fxml", event);
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
    private void handleButtonActionAtras(ActionEvent event) throws IOException{
        Picr.changeScene("LobbyApp.fxml", event);
    }

    private void actualizarImagen() {
        Image image = imagenesList.get(contador);
        imagenViewImagenes.setImage(image);
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
