package Modelo;

import Control.BaseDatos;
import java.util.ArrayList;

/**
 *
 * @author Momo
 */
public class Usuario {
    public static final int NOMBRE = 0;
    public static final int CORREO = 2;
    public static final int CLAVE = 3;
    public static final int ID = 5;
    //---------------------
    private String nombre_usuario;
    private String apellido_usuario;
    private String correo;
    private String clave;
    private String fecha_nacimiento;
    private String id_usuario;

    public Usuario() {
    }

    public Usuario(String nombre_usuario, String apellido_usuario, String correo, String clave, String fecha_nacimiento, String id_usuario) {
        this.nombre_usuario = nombre_usuario;
        this.apellido_usuario = apellido_usuario;
        this.correo = correo;
        this.clave = clave;
        this.fecha_nacimiento = fecha_nacimiento;
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public String getApellido_usuario() {
        return apellido_usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public String getClave() {
        return clave;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public void setApellido_usuario(String apellido_usuario) {
        this.apellido_usuario = apellido_usuario;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    @Override
    public String toString() {
        return "usuario{" + "nombre_usuario=" + nombre_usuario + ", apellido_usuario=" + apellido_usuario + ", correo=" + correo + ", clave=" + clave + ", fecha_nacimiento=" + fecha_nacimiento + ", id_usuario=" + id_usuario + '}';
    }
    
    public boolean insertarUsuario(ArrayList<Usuario> arrUsu){
        String sql;
        BaseDatos objBases=new BaseDatos();
        boolean conexion;
        boolean insertar=false;
        
        sql="insert into usuario (nombre_usuario,apellido_usuario,correo,clave,fecha_nacimiento) values(?,?,?,?,?)";
        
        for (Usuario arrUsu1 : arrUsu){
            conexion=objBases.crearConexion();
            if (conexion) {
                insertar = objBases.sqlInsertUsuario(sql, 
                        arrUsu1.getNombre_usuario(), arrUsu1.getApellido_usuario(),arrUsu1.getCorreo(), 
                        arrUsu1.getClave(), arrUsu1.getFecha_nacimiento());
            }
        }
        return insertar;
    }
}