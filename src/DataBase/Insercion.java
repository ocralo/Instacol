package DataBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.*;

/**
 *
 * @author Usaka Rokujou
 */
public class Insercion {

    //<editor-fold defaultstate="collapsed" desc="Usuario">
    /**
     * Metodo para insertar un usuario a la base de datos La fecha debe tener el
     * formato (AAAA/MM/DD)
     *
     * @param usuario
     * @return estado regresa el estado de la conexión, true si se logro
     * insertar el usuario, falso en caso contrario.
     */
    public static boolean sqlInsertUsuario(Usuario usuario) {

        String sql;

        if (Conexion.crearConexion()) {
            PreparedStatement ps = null;
            try {
                Tools.imprimirC(usuario.toString());

                sql = "insert into usuarios "
                        + "(nombre_usuario,"
                        + "apellido_usuario,"
                        + "correo,"
                        + "clave,"
                        + "fecha_nacimiento) "
                        + "values(?,?,?,?,?)";

                Conexion.getConexion().setAutoCommit(false);
                ps = Conexion.getConexion().prepareStatement(sql);

                ps.setString(1, usuario.getNombre());
                ps.setString(2, usuario.getApellido());
                ps.setString(3, usuario.getCorreo());
                ps.setString(4, usuario.getClave());
                ps.setString(5, usuario.getFecha());

                ps.executeUpdate();
                Conexion.getConexion().commit();

                return true;
            } catch (SQLException ex) {
                DataBase.Tools.imprimirC(ex.getMessage());
            } finally {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    DataBase.Tools.imprimirC(ex.getMessage());
                }
            }
        }
        return false;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Perfil Con ImagenPost">
    /**
     * Metodo que permite insertar un perfil INCLUIDA LA IMAGEN
     *
     * @param perfil
     * @return retorna true si la inserción fue correcta, y false si hubo algun
     * error
     */
    public static boolean sqlInsertPerfilIMG(Perfil perfil) {

        String sql;

        if (Conexion.crearConexion()) {
            PreparedStatement ps = null;
            try {

                DataBase.Tools.imprimirC(perfil.toString());

                sql = "insert into perfil "
                        + "(nombre_perfil,"
                        + "cod_usuario, "
                        + "foto_perfil)"
                        + "values(?,?,?)";

                Conexion.getConexion().setAutoCommit(false);
                ps = Conexion.getConexion().prepareStatement(sql);

                ps.setString(1, perfil.getNombrePerfil());
                ps.setInt(2, perfil.getCodUsuario());
                ps.setBinaryStream(3,
                        new FileInputStream(perfil.getFotoPerfil().getArchivo()),
                        (int) perfil.getFotoPerfil().getArchivo().length()
                );

                ps.executeUpdate();
                Conexion.getConexion().commit();

                return true;
            } catch (SQLException | FileNotFoundException | NullPointerException ex) {
                Tools.imprimirC(ex.getMessage());
            } finally {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    DataBase.Tools.imprimirC(ex.getMessage());
                }
            }
        }
        return false;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Perfil Sin ImagenPost">
    /**
     * Permite insertar un perfil SIN INCLUIR AL IMAGEN
     *
     * @param perfil
     * @return retorna true si la inserción fue correcta, y false si hubo algun
     * error
     */
    public static boolean sqlInsertPerfil(Perfil perfil) {

        String sql;

        if (Conexion.crearConexion()) {
            PreparedStatement ps = null;
            try {
                Tools.imprimirC(perfil.toString());

                sql = "insert into perfil "
                        + "(nombre_perfil,"
                        + "cod_usuario) "
                        + "values(?,?)";

                Conexion.getConexion().setAutoCommit(false);
                ps = Conexion.getConexion().prepareStatement(sql);
                ps.setString(1, perfil.getNombrePerfil());
                ps.setInt(2, perfil.getCodUsuario());

                ps.executeUpdate();
                Conexion.getConexion().commit();

                return true;
            } catch (SQLException ex) {
                DataBase.Tools.imprimirC(ex.getMessage());
            } finally {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    DataBase.Tools.imprimirC(ex.getMessage());
                }
            }
        }
        return false;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Comentario">
    /**
     * Metodo que permite la inserción de un comentario en una imagen
     *
     * @param comentario
     * @return true si el comentario fue añadido o false si hubo algun error
     */
    public static boolean sqlInsertComentario(Comentario comentario) {

        String sql;

        if (Conexion.crearConexion()) {
            PreparedStatement ps = null;
            try {
                Tools.imprimirC(comentario.toString());

                sql = "insert into comentario "
                        + "(mensaje,"
                        + "cod_perfil_comentario,"
                        + "cod_imagen_comentario)"
                        + "values(?,?,?)";

                Conexion.getConexion().setAutoCommit(false);
                ps = Conexion.getConexion().prepareStatement(sql);

                ps.setString(1, comentario.getMensaje());
                ps.setInt(2, comentario.getCodPerfilComen());
                ps.setInt(3, comentario.getCodImagenComen());

                ps.executeUpdate();
                Conexion.getConexion().commit();

                return true;
            } catch (SQLException ex) {
                DataBase.Tools.imprimirC(ex.getMessage());
            } finally {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    DataBase.Tools.imprimirC(ex.getMessage());
                }
            }
        }
        return false;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="ImagenPost">
    /**
     * Metodo que permite insertar una imagen en un perfil
     *
     * @param imagen
     * @return true si ela inserción fue exitosa o false si hubo algun error
     */
    public static boolean sqlInsertImagen(ImagenPost imagen) {

        String sql;

        if (Conexion.crearConexion()) {
            PreparedStatement ps = null;

            try {
                Tools.imprimirC(imagen.toString());

                sql = "insert into imagen "
                        + "(imagen,"
                        + "me_gusta_imagen,"
                        + "cod_perfil_imagen)"
                        + "values(?,?,?)";

                Conexion.getConexion().setAutoCommit(false);
                ps = Conexion.getConexion().prepareStatement(sql);

                ps.setBinaryStream(1,
                        new FileInputStream(imagen.getImagen().getArchivo()),
                        imagen.getImagen().getArchivo().length());

                ps.setInt(2, imagen.getMeGusta());
                ps.setInt(3, imagen.getCodImagenPerfil());

                ps.executeUpdate();
                Conexion.getConexion().commit();

                return true;
            } catch (SQLException | FileNotFoundException ex) {
                DataBase.Tools.imprimirC(ex.getMessage());
            } finally {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    DataBase.Tools.imprimirC(ex.getMessage());
                }
            }
        }
        return false;
    }
//</editor-fold>
}
