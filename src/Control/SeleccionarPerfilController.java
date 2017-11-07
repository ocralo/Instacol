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
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;

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
    private BaseDatos objBases;

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

                    idBuscar = auxDato[0];
                } catch (IOException ex) {
                    Logger.getLogger(CrearPerfilController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                actualizarPerfiles();
                

            } catch (IOException ex) {
                Logger.getLogger(PerfilController.class.getName()).log(Level.SEVERE, null, ex);
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
        
        rPerfil1.setSelected(false);
        rPerfil2.setSelected(false);
        rPerfil3.setSelected(false);
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
      
        rPerfil1.setSelected(false);
        rPerfil2.setSelected(false);
        rPerfil3.setSelected(false);
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
    
    @FXML
    private void handleRButtonAction1(ActionEvent event) throws IOException {
        rPerfil2.setSelected(false);
        rPerfil3.setSelected(false);
    }
    
    @FXML
    private void handleRButtonAction2(ActionEvent event) throws IOException {
        rPerfil1.setSelected(false);
        rPerfil3.setSelected(false);
    }
    
    @FXML
    private void handleRButtonAction3(ActionEvent event) throws IOException {
        rPerfil1.setSelected(false);
        rPerfil2.setSelected(false);
    }

    @FXML
    private void handleButtonActionSeleccionar(ActionEvent event) throws IOException {
        String perfilID = "";
        
        if(rPerfil1.isSelected())
        {
            perfilID = perfil.get(contPerfil*3).getId_perfil();
        }
        else if(rPerfil2.isSelected())
        {
            perfilID = perfil.get(contPerfil*3 + 1).getId_perfil();
        }
        else if(rPerfil3.isSelected())
        {
            perfilID = perfil.get(contPerfil*3 + 2).getId_perfil();
        }
        
        PrintWriter writer = new PrintWriter("src/Imagenes/usuario.txt", "UTF-8");
        String perfil = idBuscar + "," + perfilID;
        writer.println(perfil);
        writer.close();
        
        Picr.changeScene("LobbyApp.fxml", event);
    }
    
    private void organizarImagenes() {
        img1.setVisible(true);
        rPerfil1.setVisible(true);
        
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
    
      @FXML
    private void handleButtonActionEliminarPerfil(ActionEvent event) throws IOException{
        String perfilID = "";
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro desea eliminar su perfil?"+"\n"+"Si no tiene otro perfil su cuenta será eliminada");
        if(respuesta==0){
            if(rPerfil1.isSelected())
            {
                perfilID = perfil.get(contPerfil*3).getId_perfil();
//                rPerfil1.setVisible(false);
//                img1.setVisible(false);
                perfil.remove(contPerfil*3);
            }
            else if(rPerfil2.isSelected())
            {
                perfilID = perfil.get(contPerfil*3 + 1).getId_perfil();
//                rPerfil2.setVisible(false);
//                img2.setVisible(false);
                perfil.remove(contPerfil*3 + 1);
            }
            else if(rPerfil3.isSelected())
            {
                perfilID = perfil.get(contPerfil*3 + 2).getId_perfil();
//                rPerfil3.setVisible(false);
//                img3.setVisible(false);
                perfil.remove(contPerfil*3 + 2);
            }
            if(perfil.isEmpty()){
                objBases.sqlDeleteUsuario(idBuscar);
                Picr.changeScene("Inicio.fxml", event);
            }
//            rPerfil1.setSelected(false);
//            rPerfil2.setSelected(false);
//            rPerfil3.setSelected(false);

            objBases.EliminarPerfil(perfilID);
            
            Picr.changeScene("SeleccionarPerfil.fxml", event);
//            actualizarPerfiles();
        }
    }

    private void actualizarPerfiles() throws IOException {
        botonBajar.setVisible(false);
        botonSubir.setVisible(false);
        img1.setVisible(false);
        rPerfil1.setVisible(false);
        
        img2.setVisible(false);
        rPerfil2.setVisible(false);
        
        img3.setVisible(false);
        rPerfil3.setVisible(false);
        imagenes.clear();
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
        contadorImagenes = 0;
    }

}
