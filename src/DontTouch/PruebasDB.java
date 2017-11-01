package DontTouch;

import Control.BaseDatos;
import Modelo.imagen;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Usaka Rokujou
 */
public class PruebasDB {

    public static void main(String... arg) {
        new PruebasDB().eliminarIMG();
    }

    //<editor-fold defaultstate="collapsed" desc="Pruebas de insercion">
    public void insertarUsuario() {
        Tools.imprimirC("" + DontTouch.Insercion.sqlInsertUsuario(new Usuario("Breyner", "Albarracin", "basfv@sfitvbrs", "jaja", "1997/09/09")));
    }

    public void insertarImagen() {
        try {
            imagen i = new imagen(ImageIO.read(new File("a")), String.valueOf(5), String.valueOf(0), String.valueOf(1));
            i = new imagen(ImageIO.read(new File("a")), String.valueOf(5), String.valueOf(0), String.valueOf(1));
            i = new imagen(ImageIO.read(new File("a")), String.valueOf(5), String.valueOf(0), String.valueOf(1));

            BaseDatos base = new BaseDatos();
            if (Conexion.crearConexion()) {
                //Insercion.sqlInsertImagen(i);
            }
        } catch (IOException io) {

        }
    }

    public void insertarPerfil() {
        Imagen img = new Imagen("C:\\Users\\breyn\\Desktop\\Jacob_Frye.jpg");
        Perfil p = new Perfil("UsakaRKJ", 4, img);

        Tools.imprimirC("" + DontTouch.Insercion.sqlInsertPerfilIMG(p));
    }

    public void insertarIma() {
        Imagen img = new Imagen("C:\\Users\\breyn\\Desktop\\tecnologia.jpg");

        Tools.imprimirC("" + DontTouch.Insercion.sqlInsertImagen(new ImagenPost(img, 0, 1)));

    }

    public void insertarComen() {
        Tools.imprimirC("" + DontTouch.Insercion.sqlInsertComentario(new Comentario("FUNCIONA!!!!", 1, 1)));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Pruebas de Eliminacion">
    public void eliminarIMG() {
        Eliminar.sqlDeleteImagenPost(String.valueOf(1));
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Pruebas de Busqueda">

    public void consultarIMG() {
        ImagenPost img = Consulta.sqlConsultImagePost(String.valueOf(1));
        Tools.imprimirC(img.toString());
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Pruebas de Actualizacion">
//</editor-fold>
}
