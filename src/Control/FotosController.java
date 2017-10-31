/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.imagen;
import Modelo.perfil;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author rodrigoescobarlopez
 */
public class FotosController implements Initializable {
    
    LinkedList<imagen> listaImagenes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaImagenes=new LinkedList<>();
        String idPerfil;
        BaseDatos objBases = new BaseDatos();
        boolean conexion;
        conexion = objBases.crearConexion();
        if (conexion) {
            try {
                listaImagenes = objBases.buscarFoto();
                
            } catch (IOException ex) {
                Logger.getLogger(FotosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
