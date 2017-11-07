package Control;

import Modelo.imagen;
import Modelo.Perfil;
import Modelo.lista;
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

public class BaseDatos {

    Connection conexion;
    Statement st;

    /**
     * Permite crear una base de datos vacia
     */
    public BaseDatos() {
    }

    /**
     * Permite obtener la Conexion de la clase
     *
     * @return connection a la base de datos
     */
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
     * Permite insertar un usuario en la base de datos
     *
     * @param insert cadena con el comando SQL a ejecutar
     * @param Nombre_usuario nombre del usuario a insertar
     * @param Apellido_usuario apellido del usuario a insertar
     * @param Correo correo del usuario a insertar
     * @param Clave clave para el inicio de sesion
     * @param Fecha_nacimiento fecha de nacimiento del usuario
     * @return boleano con el resutaldo de la inserción (true=correcto / false =
     * incorrecto)
     */
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

    /**
     * Permite buscar un correo dentro de la base de datos para validaciones
     *
     * @param buscar cadena con el correo que se desea consultar
     * @return Arreglo con todos los correos encontrados
     */
    public ArrayList buscarCorreo(String buscar) {
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

    /**
     * Permite buscar una imagen a partir de varios criterios
     *
     * @param criterio cracteristica que se desea comprobar
     * @param buscarImagen valor que se validará para obtener las imagenes
     * @return Lista con las imagenes que cumplieron el criterio designado
     */
    public LinkedList<imagen> buscarImagen(String criterio, String buscarImagen) {
        LinkedList<imagen> listaImagenes = new LinkedList();
        BufferedImage img;
        Blob imagenB;
        byte[] blobAsBytes;

        try {
            ResultSet rs = st.executeQuery("SELECT * FROM imagen where " + criterio + "='" + buscarImagen + "'");
            while (rs.next()) {

                String codUsuario = rs.getString("id_imagen");
                String me_gusta = rs.getString("me_gusta");
                String codPerfilImagen = rs.getString("cod_perfil_imagen");
                imagenB = rs.getBlob("imagen");

                int blobLength = (int) imagenB.length();
                blobAsBytes = imagenB.getBytes(1, blobLength);
                imagenB.free();

                img = ImageIO.read(new ByteArrayInputStream(blobAsBytes));

                imagen imgen = new imagen(img, me_gusta, codUsuario, codPerfilImagen);

                listaImagenes.add(imgen);

            }

        } catch (SQLException | IOException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaImagenes;
    }

    /**
     * Permite eliminar un usuario de la base de datos
     *
     * @param eliminar cadena con el ID del usuario que se desea eliminar
     */
    public void sqlDeleteUsuario(String eliminar) {
        String sql = "DELETE FROM usuario WHERE id_usuario=" + eliminar + "";
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
     * @param valor
     * @return estado regresa el estado de la conexión, true si se estableció la
     * conexión, falso en caso contrario
     * @throws java.io.IOException
     */
    public LinkedList<Perfil> buscarPerfil(String criterio, String valor) throws IOException {
        LinkedList<Perfil> listaPerfil = new LinkedList();
        BufferedImage img;
        Blob imagenB;
        byte[] blobAsBytes;

        try {
            System.out.println("SELECT * FROM perfil WHERE " + criterio + " ='" + valor + "'");
            ResultSet rs = st.executeQuery("SELECT * FROM perfil WHERE " + criterio + " ='" + valor + "'");
            while (rs.next()) {

                String nombrePerfil = rs.getString("nombre_perfil");
                String codUsuario = rs.getString("cod_usuario");
                String idPerfil = rs.getString("id_perfil");
                imagenB = rs.getBlob("foto_perfil");

                int blobLength = (int) imagenB.length();
                blobAsBytes = imagenB.getBytes(1, blobLength);
                imagenB.free();

                img = ImageIO.read(new ByteArrayInputStream(blobAsBytes));

                Perfil us = new Perfil(nombrePerfil, img, idPerfil, codUsuario);

                listaPerfil.add(us);
                System.out.println(us);

            }

        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaPerfil;

    }

    /**
     * Metodo que permite obtener todas las imagenes de la base de datos
     *
     * @return lista con todas las imagenes de la base de datos
     */
    public LinkedList<imagen> buscarFoto() {
        LinkedList<imagen> listaImagenes = new LinkedList();
        BufferedImage img;
        Blob imagenB;
        byte[] blobAsBytes;

        try {
            ResultSet rs = st.executeQuery("SELECT * FROM imagen");
            while (rs.next()) {

                String codUsuario = rs.getString("id_imagen");
                String me_gusta = rs.getString("me_gusta");
                String codPerfilImagen = rs.getString("cod_perfil_imagen");
                imagenB = rs.getBlob("imagen");

                int blobLength = (int) imagenB.length();
                blobAsBytes = imagenB.getBytes(1, blobLength);
                imagenB.free();

                img = ImageIO.read(new ByteArrayInputStream(blobAsBytes));

                imagen imgen = new imagen(img, me_gusta, codUsuario, codPerfilImagen);

                listaImagenes.add(imgen);

            }

        } catch (SQLException | IOException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaImagenes;
    }

    /**
     * Permite insertar un usuario en la vase de daos
     *
     * @param perfilU Clase perfil que contiene la información del perfil a
     * insertar
     * @param ruta ruta de la imagen que se desea subir al perfil
     * @return boleano con el resutaldo de la inserción (true=correcto / false =
     * incorrecto)
     * @throws FileNotFoundException Excepcion relacionado con la existencia del
     * archivo en el disco
     * @throws IOException Excepcion relacionada con la lectura y escritura en
     * el disco duro
     */
    public boolean insertarPerfil(Perfil perfilU, String ruta) throws FileNotFoundException, IOException {

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

    /**
     * Permite pedir el ID de un usuario a partir de su correo electronico
     *
     * @param correo correo electronico con el cual obtener el id
     * @return cadena que contiene el id del usuario
     */
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

    /**
     * Permite insertar una imagen en la base de datos
     *
     * @param ImagenU Clase imagen que contiene toda la información de la imagen
     * a insertar
     * @param ruta ruta de la imagen en el disco duro
     * @return boleano con el resutaldo de la inserción (true=correcto / false =
     * incorrecto)
     * @throws FileNotFoundException Excepcion relacionado con la existencia del
     * archivo en el disco
     * @throws IOException Excepcion relacionada con la lectura y escritura en
     * el disco duro
     */
    public boolean InsertImagen(imagen ImagenU, String ruta) throws FileNotFoundException, IOException {

        String sql = "INSERT INTO imagen (imagen,me_gusta,id_imagen,cod_perfil_imagen) VALUES(?,?,?,?)";
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

    /**
     * Permite eliminar un perfil a partir de su ID
     *
     * @param eliminar cadena con el id del perfil a eliminar
     */
    public void EliminarPerfil(String eliminar) {
        String sql = "DELETE FROM perfil WHERE id_perfil=" + eliminar;
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Permite obtener una lista con listas de imagenes de los perfiles dentro
     * de la base de datos
     *
     * @return lista de listas con las kimagenes de los perfiles
     */
    public LinkedList<LinkedList<Object>> buscarImagenPerfil() {
        LinkedList<LinkedList<Object>> listaConsulta = new LinkedList();
        BufferedImage img;
        BufferedImage imgPerfil;
        Blob imagenPerfil;
        Blob imagen;
        byte[] blobAsBytes;

        try {
            ResultSet rs = st.executeQuery("SELECT * FROM imagen i, perfil p where i.cod_perfil_imagen=p.id_perfil");
            while (rs.next()) {
                LinkedList<Object> resultado = new LinkedList<>();
                String idImagen = rs.getString("id_imagen");
                String me_gusta = rs.getString("me_gusta");
                String codPerfilImagen = rs.getString("cod_perfil_imagen");
                imagen = rs.getBlob("imagen");

                int blobLengthImagen = (int) imagen.length();
                blobAsBytes = imagen.getBytes(1, blobLengthImagen);
                imagen.free();

                img = ImageIO.read(new ByteArrayInputStream(blobAsBytes));
                imagen i = new imagen(img, me_gusta, idImagen, codPerfilImagen);

                String nombrePerfil = rs.getString("nombre_perfil");
                String codUsuario = rs.getString("cod_usuario");
                String idPerfil = rs.getString("id_perfil");
                imagenPerfil = rs.getBlob("foto_perfil");

                int blobLengthPerfil = (int) imagenPerfil.length();
                blobAsBytes = imagenPerfil.getBytes(1, blobLengthPerfil);
                imagenPerfil.free();

                imgPerfil = ImageIO.read(new ByteArrayInputStream(blobAsBytes));

                Perfil p = new Perfil(nombrePerfil, imgPerfil, idPerfil, codUsuario);

                resultado.add(i);
                resultado.add(p);

                listaConsulta.add((LinkedList<Object>) resultado.clone());

                resultado.clear();

            }

        } catch (SQLException | IOException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaConsulta;
    }

    /**
     * Permite insertar una lista de imagenes en la base de datos
     *
     * @param Lista clase lista que contiene la información a insertar
     * @return boleano con el resutaldo de la inserción (true=correcto / false =
     * incorrecto)
     */
    public boolean InsertLista(lista Lista) {
        String sql = "INSERT INTO lista(nombre_lista,id_lista, cod_perfil_lista) values(?,?,?)";
        PreparedStatement ps = null;
        try {
            conexion.setAutoCommit(false);
            ps = conexion.prepareStatement(sql);
            ps.setString(1, Lista.getNombre_lista());
            ps.setInt(2, Integer.parseInt(Lista.getId_lista()));
            ps.setInt(3, Integer.parseInt(Lista.getCod_perfil_lista()));
            ps.executeUpdate();
            conexion.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    /**
     * Permite obtener una lista a partir de diversos criterios
     *
     * @param criterio Cadena con alusiva a un campo de la tabla
     * @param valor Valor que debe tener dicho campo para ser usado en el
     * aplicativo
     * @return una Lista con las listas que cumplieron dicho criterio
     * @throws IOException
     */
    public LinkedList<lista> buscarLista(String criterio, String valor) throws IOException {
        LinkedList<lista> listalista = new LinkedList();
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM lista WHERE " + criterio + "='" + valor + "'");
            while (rs.next()) {
                String nombreLista = rs.getString("nombre_lista");
                String idLista = rs.getString("id_lista");
                String codPerfilLista = rs.getString("cod_perfil_lista");

                lista us = new lista(nombreLista, idLista, codPerfilLista);

                listalista.add(us);
                System.out.println(us);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listalista;
    }

    /**
     * Permite insertar imagenes a una lista
     *
     * @param idListaImagen identificador de la lista (debe proporcionarlo la
     * base de datos)
     * @param codLista codigo de la lista donde se desea isnertar la imagen
     * @param codImagen codigo de la imagen que se desea insertar en la lista
     * @return boleano con el resutaldo de la inserción (true=correcto / false =
     * incorrecto)
     */
    public boolean InsertListaImagen(String idListaImagen, String codLista, String codImagen) {
        String sql = "INSERT INTO lista_imagen(id_lista_imagen,cod_lista, cod_imagen) VALUES(?,?,?)";
        PreparedStatement ps = null;
        try {
            conexion.setAutoCommit(false);
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(idListaImagen));
            ps.setInt(2, Integer.parseInt(codLista));
            ps.setInt(3, Integer.parseInt(codImagen));
            ps.executeUpdate();
            conexion.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    /**
     * Permite obtener las imagenes de una lista usando un criterio determinado
     *
     * @param criterio Columna que debe cumplir con el valor para obtener la
     * lista de imagenes
     * @param valor Valor que debe cumplir una columna determinada para poder
     * devolver una lista de imagenes
     * @return lista de imagen que contiene las imagenes que cumplieron con el
     * criterio
     * @throws IOException
     */
    public LinkedList<imagen> buscarImagenesLista(String criterio, String valor) throws IOException {
        LinkedList<imagen> listaimagenes = new LinkedList();
        BufferedImage img;
        Blob imagenB;
        byte[] blobAsBytes;
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM imagen i join lista_imagen li on i.id_imagen=li.cod_imagen join lista l on li.cod_lista=l.id_lista where l." + criterio + "='" + valor + "'");
            while (rs.next()) {
                String codUsuario = rs.getString("id_imagen");
                String me_gusta = rs.getString("me_gusta");
                String codPerfilImagen = rs.getString("cod_perfil_imagen");
                imagenB = rs.getBlob("imagen");

                int blobLength = (int) imagenB.length();
                blobAsBytes = imagenB.getBytes(1, blobLength);
                imagenB.free();

                img = ImageIO.read(new ByteArrayInputStream(blobAsBytes));

                imagen imgen = new imagen(img, me_gusta, codUsuario, codPerfilImagen);

                listaimagenes.add(imgen);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaimagenes;
    }

    /**
     * Metodo que permite dar like a una imagen
     *
     * @param idPerfilImagen identificador de la imagen a la cual se data un Me
     * Gusta
     * @param codPerfilPI codigo del perfil que realiza el Me gusta
     * @param codImagenPI codigo de la imagen a la cual se dara Me gusta
     * @return boleano con el resutaldo de la inserción (true=correcto / false =
     * incorrecto)
     */
    public boolean CrearLike(String idPerfilImagen, String codPerfilPI, String codImagenPI) {
        String sql = "INSERT INTO perfil_imagen(id_perfil_imagen,cod_perfil_pi,cod_imagen_pi) VALUES(?,?,?)";
        PreparedStatement ps = null;
        try {
            conexion.setAutoCommit(false);
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(idPerfilImagen));
            ps.setInt(2, Integer.parseInt(codPerfilPI));
            ps.setInt(3, Integer.parseInt(codImagenPI));
            ps.executeUpdate();
            conexion.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    /**
     * Metodo que permite rechazar un like de una imagen
     *
     * @param eliminar codigo del perfil que dio un like previamente y ahora lo
     * retira
     * @return boleano con el resutaldo de la inserción (true=correcto / false =
     * incorrecto)
     * @throws IOException
     */
    public boolean EliminarLike(String eliminar) throws IOException {
        String sql = "DELETE FROM perfil_imagen WHERE cod_perfil_pi=" + eliminar;
        System.out.println(sql);
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Permite obtener un numero de like totales
     *
     * @return Entero con el numero de like totales
     * @throws IOException
     */
    public int NumeroLikes() throws IOException {
        int total = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT COUNT(pi.id_perfil_imagen) AS suma FROM perfil_imagen pi");
            while (rs.next()) {
                String megusta = rs.getString("suma");
                total = Integer.parseInt(megusta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public void UpdateLike(String valor, String id_imagen) {
        String sql = "UPDATE imagen SET me_gusta='" + valor + "' WHERE id_imagen ='" + id_imagen + "'";
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement(sql);
            ps.execute();
            conexion.commit();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public LinkedList<String> buscarPerfilImagen(String valor) throws IOException {
        LinkedList<String> prueba = new LinkedList();
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM perfil_imagen where cod_perfil_pi ='" + valor + "'");
            while (rs.next()) {
                String idPerfilImagen = rs.getString("id_perfil_imagen");
                String codPerfilPI = rs.getString("cod_perfil_pi");
                String codImagenPI = rs.getString("cod_imagen_pi");

                prueba.add(idPerfilImagen);
                prueba.add(codPerfilPI);
                prueba.add(codImagenPI);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prueba;
    }

    public boolean insertarComentario(String idPerfil, String idImagen, String comentario) {
        String sql = "INSERT INTO comentario(mensaje,cod_perfil_comentario, cod_imagen_comentario) VALUES(?,?,?)";
        PreparedStatement ps = null;
        try {
            conexion.setAutoCommit(false);
            ps = conexion.prepareStatement(sql);
            ps.setString(1, comentario);
            ps.setInt(2, Integer.parseInt(idPerfil));
            ps.setInt(3, Integer.parseInt(idImagen));
            ps.executeUpdate();
            conexion.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public LinkedList<LinkedList<String>> buscarComentariosPerfil(String idImagen) {
        LinkedList<LinkedList<String>> comentarios = new LinkedList();

        try {

            ResultSet rs = st.executeQuery("select * from comentario c, imagen i, perfil p where c.cod_imagen_comentario=i.id_imagen and c.cod_perfil_comentario=p.id_perfil and i.id_imagen=" + idImagen);
            while (rs.next()) {
                LinkedList<String> infoComentarios = new LinkedList<>();
                String nombrePerfil = rs.getString("nombre_perfil");
                String comentario = rs.getString("mensaje");

                infoComentarios.add(nombrePerfil);
                infoComentarios.add(comentario);

                comentarios.add((LinkedList<String>) infoComentarios.clone());

                infoComentarios.clear();
            }

        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return comentarios;
    }
}
