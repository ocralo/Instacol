package Control;

/**
 *
 * @author Momo
 */
//import Controller.Imagen;
import DontTouch.Conexion;
import DontTouch.Tools;
import Modelo.imagen;
import Modelo.perfil;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

public class BaseDatos {

    Connection conexion;
    Statement st;

    public BaseDatos() {
        //conexion
    }

    public Connection getConexion() {
        return conexion;
    }

    /**
     * Método utilizado para establecer la conexión con la base de datos
     *
     * @return estado regresa el estado de la conexión, true si se estableció la
     * conexión, falso en caso contrario
     */
    public boolean crearConexion() {
        try {
            String usuario = "InstaColAdmin";
            String clave = "instacol";
            String bd = "insta_col";
            Class.forName("com.mysql.jdbc.Driver");                                      //user  //pass
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + bd, usuario, clave);
            st = conexion.createStatement();
        } catch (SQLException | ClassNotFoundException ex) {
            return false;
        }

        return true;
    }

    /**
     *
     * Método utilizado para realizar las instrucciones: INSERT, DELETE y UPDATE
     *
     * @param sql Cadena que contiene la instrucción SQL a ejecutar
     * @return estado regresa el estado de la ejecución, true(éxito) o
     * false(error)
     *
     */
    public boolean ejecutarSQL(String sql) {
        try {
            Statement sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     *
     * Método utilizado para realizar la instrucción SELECT
     *
     * @param sql Cadena que contiene la instrucción SQL a ejecutar
     * @return resultado regresa los registros generados por la consulta
     *
     */
    public String ejecutarSQLSelect(String sql) {
        ResultSet rs;
        int id;
        String nom = "";
        String tel = "";
        String dir = "";
        String concatenar = "";

        try {
            Statement sentencia = conexion.createStatement();
            rs = sentencia.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt(1);
                nom = rs.getNString("nombreusuario");
                tel = rs.getNString("cedulausuario");
                dir = rs.getNString("celusuario");

                concatenar = id + " " + nom + " " + tel + " " + dir;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

        return concatenar;
    }

    public boolean sqlInsertUsuario(String insert, String Nombre_usuario, String Apellido_usuario, String Correo, String Clave, String Fecha_nacimiento) {
        // String insert = "insert into Imagenes(imagen,nombre) values(?,?)";
        PreparedStatement ps = null;
        try {
            conexion.setAutoCommit(false);
            ps = conexion.prepareStatement(insert);
            ps.setString(1, Nombre_usuario);
            ps.setString(2, Apellido_usuario);
            ps.setString(3, Correo);
            ps.setString(4, Clave);
            ps.setString(5, Fecha_nacimiento);
            //ps.setString(2, nombre);
            ps.executeUpdate();
            conexion.commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
            } catch (Exception ex) {
                Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public ArrayList buscarCorreo(String buscar) throws IOException {
        ArrayList arrElementos = new ArrayList();
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM usuario WHERE correo='" + buscar + "'");
            while (rs.next()) {
                String nombreUsuario = rs.getObject("nombre_usuario").toString();
                String apellidoUsuario = rs.getObject("apellido_usuario").toString();
                String correoUsuario = rs.getObject("correo").toString();
                String claveUsuario = rs.getObject("clave").toString();
                String fechaNacimientoUsuario = rs.getObject("fecha_nacimiento").toString();
                String idUsuario = rs.getObject("id_usuario").toString();

                arrElementos.add(nombreUsuario);
                arrElementos.add(apellidoUsuario);
                arrElementos.add(correoUsuario);
                arrElementos.add(claveUsuario);
                arrElementos.add(fechaNacimientoUsuario);
                arrElementos.add(idUsuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arrElementos;
    }

    public BufferedImage buscarImagen(int buscarImagen) throws IOException {
        BufferedImage img = null;
        Blob imagenB;
        byte[] blobAsBytes;
        String nombre;
        try {
            ResultSet rs = st.executeQuery("SELECT imagen FROM imagen WHERE id_imagen ='" + buscarImagen + "'");

            System.out.println("sql");
            while (rs.next()) {
                imagenB = rs.getBlob("imagen");
                nombre = rs.getString("me_gusta_imagen");

                int blobLength = (int) imagenB.length();
                blobAsBytes = imagenB.getBytes(1, blobLength);
                imagenB.free();

                img = ImageIO.read(new ByteArrayInputStream(blobAsBytes));

            }

        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return img;
        //return img;
    }

    public void sqlDeleteUsuario(String eliminar) throws IOException {
        String sql = "DELETE FROM usuarios WHERE id_usuario=" + eliminar + "";
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método utilizado para establecer la conexión con la base de datos
     * insta_col
     *
     * @param buscarId
     * @return estado regresa el estado de la conexión, true si se estableció la
     * conexión, falso en caso contrario
     * @throws java.io.IOException
     */
    public LinkedList buscarPerfil(String buscarId) throws IOException {
        LinkedList<perfil> listaUsuario = new LinkedList();
        BufferedImage img;
        Blob imagenB;
        byte[] blobAsBytes;

        try {
            ResultSet rs = st.executeQuery("SELECT * FROM perfil WHERE id_perfil ='" + buscarId + "'");
            while (rs.next()) {

                String nombrePerfil = rs.getString("nombre_perfil");
                String codUsuario = rs.getString("cod_usuario");
                String idPerfil = rs.getString("id_perfil");
                imagenB = rs.getBlob("foto_perfil");

                int blobLength = (int) imagenB.length();
                blobAsBytes = imagenB.getBytes(1, blobLength);
                imagenB.free();

                img = ImageIO.read(new ByteArrayInputStream(blobAsBytes));

                perfil us = new perfil(nombrePerfil, img, idPerfil, codUsuario);

                listaUsuario.add(us);

            }

        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaUsuario;

    }

    public LinkedList<imagen> buscarFoto() {
        LinkedList<imagen> listaImagenes = new LinkedList();
        BufferedImage img;
        Blob imagenB;
        byte[] blobAsBytes;

        try {
            ResultSet rs = st.executeQuery("SELECT * FROM imagen");
            while (rs.next()) {

                String megusta = rs.getString("me_gusta");
                String codUsuario = rs.getString("id_imagen");
                String codPerfilImagen = rs.getString("cod_perfil_imagen");
                imagenB = rs.getBlob("imagen");

                int blobLength = (int) imagenB.length();
                blobAsBytes = imagenB.getBytes(1, blobLength);
                imagenB.free();

                img = ImageIO.read(new ByteArrayInputStream(blobAsBytes));

                imagen imgen = new imagen(img, megusta, codUsuario, codPerfilImagen);

                listaImagenes.add(imgen);

            }

        } catch (SQLException | IOException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaImagenes;
    }

    public boolean insertarPerfil(perfil perfilU, String ruta) throws FileNotFoundException, IOException {

        String sql = "INSERT INTO perfil (nombre_perfil,foto_perfil,cod_usuario) VALUES(?,?,?)";
        PreparedStatement ps = null;
        System.out.println(ruta);
        System.out.println(perfilU.getNombre_perfil());
        System.out.println(Integer.parseInt(perfilU.getCod_usuario()));
        try {
            conexion.setAutoCommit(false);
            File file = new File(ruta);
            FileInputStream fis = new FileInputStream(file);
            ps = conexion.prepareStatement(sql);
            ps.setString(1, perfilU.getNombre_perfil());
            ps.setBinaryStream(2, fis, (int) file.length());
            ps.setInt(3, Integer.parseInt(perfilU.getCod_usuario()));

            ps.executeUpdate();
            conexion.commit();

            System.out.println("lo subi");
            return true;
        } catch (SQLException ex) {
        } finally {
            try {
                ps.close();

            } catch (SQLException ex) {
            }
        }
        return false;
    }

    public String pedirUsuario(String correo) {
        String idUsuario = "";
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM usuario WHERE correo='" + correo + "'");
            while (rs.next()) {
                String nombreUsuario = rs.getObject("nombre_usuario").toString();
                String apellidoUsuario = rs.getObject("apellido_usuario").toString();
                String correoUsuario = rs.getObject("correo").toString();
                String claveUsuario = rs.getObject("clave").toString();
                String fechaNacimientoUsuario = rs.getObject("fecha_nacimiento").toString();
                idUsuario = rs.getObject("id_usuario").toString();

            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return idUsuario;
    }

    public boolean InsertImagen(imagen ImagenU, String ruta) throws FileNotFoundException, IOException {

        String sql = "INSERT INTO imagen (imagen,me_gusta_imagen,id_imagen,cod_perfil_imagen) VALUES(?,?,?,?)";
        PreparedStatement ps = null;
        try {
            conexion.setAutoCommit(false);
            File file = new File(ruta);
            FileInputStream fis = new FileInputStream(file);
            ps = conexion.prepareStatement(sql);
            ps.setBinaryStream(1, fis, (int) file.length());
            ps.setInt(2, Integer.parseInt(ImagenU.getMe_gusta()));
            ps.setInt(3, Integer.parseInt(ImagenU.getId_imagen()));
            ps.setInt(4, Integer.parseInt(ImagenU.getCod_perfil_imagen()));

            ps.executeUpdate();
            conexion.commit();

            System.out.println("lo subi");
            return true;
        } catch (SQLException ex) {
        } finally {
            try {
                ps.close();

            } catch (SQLException ex) {
            }
        }
        return false;
    }
    
    //<editor-fold defaultstate="collapsed" desc="ImagenPost">
    /**
     * Metodo que permite insertar una imagen en un perfil
     *
     * @param imagen
     * @return true si ela inserción fue exitosa o false si hubo algun error
     */
    public boolean sqlInsertImagen(imagen imagen) {

        String sql;

        if (crearConexion()) {
            PreparedStatement ps = null;

            try {
                sql = "insert into imagen "
                        + "(imagen,"
                        + "me_gusta,"
                        + "cod_perfil_imagen)"
                        + "values(?,?,?)";

                getConexion().setAutoCommit(false);
                ps = getConexion().prepareStatement(sql);

                File f = new File(imagen.getRuta());
                FileInputStream fis = new FileInputStream(f);

                ps.setBinaryStream(1, fis, f.length());
                ps.setString(2, imagen.getMe_gusta());
                ps.setString(3, imagen.getCod_perfil_imagen());

                ps.executeUpdate();
                getConexion().commit();

                return true;
            } catch (SQLException | FileNotFoundException ex) {
                Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    DontTouch.Tools.imprimirC(ex.getMessage());
                }
            }
        }
        return false;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Incrementar MeGusta">
    public boolean agregarLike(imagen img) {

        try {
            if (crearConexion()) {
                String sql = "UPDATE imagen SET me_gusta=" + img.getMe_gusta() + " WHERE id_imagen=" + img.getId_imagen();
                getConexion().setAutoCommit(false);
                PreparedStatement ps = getConexion().prepareStatement(sql);
                ps.executeUpdate();
                getConexion().commit();

                return true;
            }
        } catch (SQLException ex) {

        }
        return false;
    }
    //</editor-fold>
}

