package DontTouch;

import java.sql.SQLException;

/**
 *
 * @author Usaka Rokujou
 */
public class Eliminar {

    //<editor-fold defaultstate="collapsed" desc="Eliminar una ImagenPost">
    public static boolean sqlDeleteImagenPost(String id_imagen) {
        try {
            if (Conexion.crearConexion()) {
                String delete = "DELETE FROM imagen where id_imagen=" + id_imagen;
                Conexion.getSt().execute(delete);
                return true;
            }
            return false;
        } catch (SQLException ex) {
            return false;
        }
    }
//</editor-fold>
}
