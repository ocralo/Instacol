package Modelo;

import Control.BaseDatos;
import java.util.ArrayList;

public class Usuario {

    private String nombre_usuario;
    private String apellido_usuario;
    private String correo;
    private String clave;
    private String fecha_nacimiento;
    private String id_usuario;
    private static int CLAVE = 3;
    private static int ID = 5;
    private static int CORREO = 2;

    /**
     * Permite crear una instancia de la clase usuario
     *
     * @param nombre_usuario - nomrbe del usuario que va a trabajar
     * @param apellido_usuario - apellido del usuario que v a atrabajr
     * @param correo - correo electronico del usuario
     * @param clave - clave que el usuario va o utitlizo para iniciar sesion
     * @param fecha_nacimiento - fecha de nacimiento del usuario
     * @param id_usuario - ID del usuario (debe proporcionarlo la base de datos)
     */
    public Usuario(String nombre_usuario, String apellido_usuario, String correo, String clave, String fecha_nacimiento, String id_usuario) {
        this.nombre_usuario = nombre_usuario;
        this.apellido_usuario = apellido_usuario;
        this.correo = correo;
        this.clave = clave;
        this.fecha_nacimiento = fecha_nacimiento;
        this.id_usuario = id_usuario;
    }

    /**
     * Permite crear un usuario Vacio
     */
    public Usuario() {
    }

    /**
     * Permite insertar Usuarios a la base de datos
     *
     * @param arrUsu Lista que contiene un numero n de usuarios a insertar en la
     * base de datos
     * @return boleano con el resutaldo de la inserción (true=correcto / false =
     * incorrecto)
     */
    public boolean insertarUsuario(ArrayList<Usuario> arrUsu) {
        String sql;
        BaseDatos objBases = new BaseDatos();
        boolean conexion;
        boolean insertar = false;

        sql = "insert into usuario (nombre_usuario,apellido_usuario,correo,clave,fecha_nacimiento) values(?,?,?,?,?)";

        for (Usuario arrUsu1 : arrUsu) {
            conexion = objBases.crearConexion();
            if (conexion) {
                insertar = objBases.sqlInsertUsuario(sql,
                        arrUsu1.getNombre_usuario(), arrUsu1.getApellido_usuario(), arrUsu1.getCorreo(),
                        arrUsu1.getClave(), arrUsu1.getFecha_nacimiento());
            }
        }
        return insertar;
    }

    //<editor-fold defaultstate="collapsed" desc="Gtters && Setters">
    /**
     * @return the nombre_usuario
     */
    public String getNombre_usuario() {
        return nombre_usuario;
    }

    /**
     * @param nombre_usuario the nombre_usuario to set
     */
    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    /**
     * @return the apellido_usuario
     */
    public String getApellido_usuario() {
        return apellido_usuario;
    }

    /**
     * @param apellido_usuario the apellido_usuario to set
     */
    public void setApellido_usuario(String apellido_usuario) {
        this.apellido_usuario = apellido_usuario;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * @return the fecha_nacimiento
     */
    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    /**
     * @param fecha_nacimiento the fecha_nacimiento to set
     */
    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    /**
     * @return the id_usuario
     */
    public String getId_usuario() {
        return id_usuario;
    }

    /**
     * @param id_usuario the id_usuario to set
     */
    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * @return the CLAVE
     */
    public static int getCLAVE() {
        return CLAVE;
    }

    /**
     * @param aCLAVE the CLAVE to set
     */
    public static void setCLAVE(int aCLAVE) {
        CLAVE = aCLAVE;
    }

    /**
     * @return the ID
     */
    public static int getID() {
        return ID;
    }

    /**
     * @param aID the ID to set
     */
    public static void setID(int aID) {
        ID = aID;
    }

    /**
     * @return the CORREO
     */
    public static int getCORREO() {
        return CORREO;
    }

    /**
     * @param aCORREO the CORREO to set
     */
    public static void setCORREO(int aCORREO) {
        CORREO = aCORREO;
    }
    //</editor-fold>

    /**
     * Permite convertir en String el objeto actual
     *
     * @return Cadena con la información de la lista
     */
    @Override
    public String toString() {
        return "usuario{" + "nombre_usuario=" + getNombre_usuario() + ", apellido_usuario=" + getApellido_usuario() + ", correo=" + getCorreo() + ", clave=" + getClave() + ", fecha_nacimiento=" + getFecha_nacimiento() + ", id_usuario=" + getId_usuario() + '}';
    }
}
