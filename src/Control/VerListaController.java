/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.imagen;
import Modelo.lista;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Momo
 */
public class VerListaController implements Initializable {

    private BaseDatos objBases;
    private String idLista;
    private int contador;
    private LinkedList<Image> imagenesList;
    private LinkedList<imagen> imagenes;
    
    @FXML
    Label LabelNombreLista;
    @FXML
    private ImageView imageView1;
    @FXML
    private Button botonAnteriorImagen;
    @FXML
    private Button botonSiguienteImagen;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contador = 0;
        imagenesList = new LinkedList<>();
        imagenes = new LinkedList<>();
        
        objBases = new BaseDatos();
        boolean conexion;
        conexion = objBases.crearConexion();
        if (conexion) {
            BufferedReader in = null;
            try {
                in = new BufferedReader(new FileReader("src/Imagenes/usuario.txt"));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(VerListaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                String aux = in.readLine();
                String[] auxDato = aux.split(",");
                idLista = auxDato[3];
            } catch (IOException ex) {
                Logger.getLogger(VerListaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                imagenes = objBases.buscarImagenesLista("id_lista", idLista);
                LinkedList<lista> lista =objBases.buscarLista("id_lista", idLista);
                LabelNombreLista.setText(lista.getFirst().getNombre_lista());
            } catch (IOException ex) {
                Logger.getLogger(VerListaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (imagen imagen : imagenes) {
                Image image = SwingFXUtils.toFXImage((BufferedImage) imagen.getImagen(), null);
                imagenesList.add(image);
            }
            if(imagenes.size() > 0)
            {
                imageView1.setImage(imagenesList.getFirst());
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
    private void handleButtonActionAtras(ActionEvent event) throws IOException{
        Picr.changeScene("Perfil.fxml", event);
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
    
    private void actualizarImagen() {
        Image image = imagenesList.get(contador);
        imageView1.setImage(image);
    }
}
