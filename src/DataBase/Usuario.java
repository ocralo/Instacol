package DataBase;

/**
 *
 * @author rodrigoescobarlopez
 */
public class Usuario extends Objeto {

    private String nombre, apellido, correo, clave;
    private String fecha;
    private int idusuario;

    public Usuario(String nombre, String apellido, String correo, String clave, String fecha, int idusuario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.clave = clave;
        this.fecha = fecha;
        this.idusuario = idusuario;
    }

    public Usuario(String nombre, String apellido, String correo, String clave, String fecha) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.clave = clave;
        this.fecha = fecha;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters && Setters">
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "Usuario{\n"
                + "nombre=" + nombre + "\n"
                + "apellido=" + apellido + "\n"
                + "correo=" + correo + "\n"
                + "clave=" + clave + "\n"
                + "fecha=" + fecha + "\n"
                + "idusuario=" + idusuario + '}';
    }

    /**
     * Metodo que realiza un clonado del usuario actual
     *
     * @return clon del objeto actual
     */
    @Override
    public Objeto clon() {
        return new Usuario(nombre, apellido, correo, clave, fecha, idusuario);
    }

    /**
     * Metodo que permite comprar dos perfiles por su id
     *
     * @param obj
     * @return returna 0 si son iguales, 1 si obj es menor o -1 si obj es mayor
     */
    @Override
    public int igual(Objeto obj) {
        Usuario us = (Usuario) obj;

        if (idusuario > us.getIdusuario()) {
            return 1;
        } else if (idusuario < us.getIdusuario()) {
            return -1;
        }
        return 0;
    }
}
