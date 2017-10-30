package DontTouch;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.imageio.ImageIO;

/**
 *
 * @author Usaka Rokujou
 */
public class Consulta {

    //<editor-fold defaultstate="collapsed" desc="Consultar Imagen Por Perfil">
    public static LinkedList<ImagenPost> sqlConsultImagePerfil(String id_Perfil) {
        LinkedList<ImagenPost> imagenes = new LinkedList<>();
        try {
            String query = "SELECT imagen FROM imagen WHERE cod_perfil_imagen=" + id_Perfil;
            ResultSet rs = Conexion.getSt().executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id_imagen");
                int meGusta = rs.getInt("me_gusta");
                int codImagenPerfil = rs.getInt("cod_perfil_imagen");
                Blob blob = rs.getBlob("imagen");

                int blobLength = (int) blob.length();
                byte[] blobAsBytes = blob.getBytes(1, blobLength);
                blob.free();

                BufferedImage brim = ImageIO.read(new ByteArrayInputStream(blobAsBytes));
                Imagen i = new Imagen(brim);

                imagenes.add(new ImagenPost(i, id, meGusta, codImagenPerfil));
            }

        } catch (SQLException | IOException ex) {
        }

        return imagenes;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Consultar Imagen Por Perfil">
    public static LinkedList<ImagenPost> sqlConsultImagePost(String id_imagen) {
        LinkedList<ImagenPost> imagenes = new LinkedList<>();
        try {
            String query = "SELECT imagen FROM imagen WHERE cod_perfil_imagen=" + id_imagen;
            ResultSet rs = Conexion.getSt().executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id_imagen");
                int meGusta = rs.getInt("me_gusta");
                int codImagenPerfil = rs.getInt("cod_perfil_imagen");
                Blob blob = rs.getBlob("imagen");

                int blobLength = (int) blob.length();
                byte[] blobAsBytes = blob.getBytes(1, blobLength);
                blob.free();

                BufferedImage brim = ImageIO.read(new ByteArrayInputStream(blobAsBytes));
                Imagen i = new Imagen(brim);

                imagenes.add(new ImagenPost(i, id, meGusta, codImagenPerfil));
            }

        } catch (SQLException | IOException ex) {
        }

        return imagenes;
    }
}
