/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.MeGustaImagen;
import Modelo.imagen;
import Modelo.Perfil;
import Modelo.lista;
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
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Momo
 */
public class AgregarImagenController implements Initializable {

    @FXML
    private ImageView imagen;
    @FXML
    private ComboBox ComboBoxListas;

    private String srcimg;
    private Image image;
    private File file;
    private String idPerfil;
    BaseDatos objBases;
    LinkedList<String> nombrelista;
    ObservableList<String> lista;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nombrelista = new LinkedList();
        objBases = new BaseDatos();
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
                Logger.getLogger(AgregarImagenController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                String aux = in.readLine();
                String[] auxDato = aux.split(",");
                idPerfil = auxDato[1];
            } catch (IOException ex) {
                Logger.getLogger(AgregarImagenController.class.getName()).log(Level.SEVERE, null, ex);
            }
            LinkedList<lista> listas = objBases.buscarLista("cod_perfil_lista", idPerfil);
            lista = FXCollections.observableArrayList();
            for (lista Lista : listas) {
                lista.add(Lista.getNombre_lista());
            }
            ComboBoxListas.setItems(lista);
            
        } catch (IOException ex) {
            Logger.getLogger(AgregarImagenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void SeleccionImagen(ActionEvent event) throws IOException {
        Stage st = new Stage(StageStyle.UTILITY);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Archivos JPG");
        //filtro para solo traer imagenes
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg"));
        //trae la ruta del archivo
        file = fileChooser.showOpenDialog(st);
        image = new Image(file.toURI().toString());

        imagen.setImage(image);
        srcimg = file.getAbsolutePath();
    }

    @FXML
    private void Subir(ActionEvent event) throws IOException {
        boolean conexion;
        conexion = objBases.crearConexion();
        if (conexion) {
            BufferedImage imageB = SwingFXUtils.fromFXImage(image, null);
            int id_imagen = 0;
            String codPerfilImagen = idPerfil;
            imagen Imagen = new imagen(imageB,String.valueOf(id_imagen), String.valueOf(codPerfilImagen));
            objBases.InsertImagen(Imagen, srcimg);
            LinkedList<imagen> img =objBases.buscarFoto();
            for(String listaNombreListas : nombrelista){
                LinkedList<lista> lista =objBases.buscarLista("nombre_lista", listaNombreListas);
                objBases.InsertListaImagen(String.valueOf(0),lista.getLast().getId_lista(),img.getLast().getId_imagen());
            }
            Picr.changeScene("LobbyApp.fxml", event);
        } else {
            System.out.println("No se pudo realizar la conexión");
        }
    }

    @FXML
    private void AgregarALista(ActionEvent event) throws IOException {
        String ListaSeleccionada = ComboBoxListas.getValue() + "";
        ComboBoxListas.getItems().remove(ListaSeleccionada);
        nombrelista.add(ListaSeleccionada);
        System.out.println(ListaSeleccionada);
    }

    @FXML
    private void handleButtonActionCrearLista(ActionEvent event) {
        String nombre = JOptionPane.showInputDialog(null, "Nombre de la nueva lista");
        lista Lista = new lista(nombre, String.valueOf(0), idPerfil);
        objBases.InsertLista(Lista);
        lista.add(nombre);
        ComboBoxListas.setItems(lista);
    }
}
