package Modelo;

public class comentario {

    private String mensaje;
    private String id_comentario;
    private String cod_perfil_comentario;
    private String cod_imagen_comentario;

    /**
     * Permite crear un comentario dentro del programa
     *
     * @param mensaje - Cadena que posee el texto que se mostrara en el
     * comentario
     * @param id_comentario - Id del comentario (Debe proporcioanrlo la base de
     * datos)
     * @param cod_perfil_comentario codigo del perfil asignado al comntario
     * @param cod_imagen_comentario codigo de la imagen donde se realizo el
     * comentario
     */
    public comentario(String mensaje, String id_comentario, String cod_perfil_comentario, String cod_imagen_comentario) {
        this.mensaje = mensaje;
        this.id_comentario = id_comentario;
        this.cod_perfil_comentario = cod_perfil_comentario;
        this.cod_imagen_comentario = cod_imagen_comentario;
    }

//<editor-fold defaultstate="collapsed" desc="Getters && Setters">
    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the id_comentario
     */
    public String getId_comentario() {
        return id_comentario;
    }

    /**
     * @param id_comentario the id_comentario to set
     */
    public void setId_comentario(String id_comentario) {
        this.id_comentario = id_comentario;
    }

    /**
     * @return the cod_perfil_comentario
     */
    public String getCod_perfil_comentario() {
        return cod_perfil_comentario;
    }

    /**
     * @param cod_perfil_comentario the cod_perfil_comentario to set
     */
    public void setCod_perfil_comentario(String cod_perfil_comentario) {
        this.cod_perfil_comentario = cod_perfil_comentario;
    }

    /**
     * @return the cod_imagen_comentario
     */
    public String getCod_imagen_comentario() {
        return cod_imagen_comentario;
    }

    /**
     * @param cod_imagen_comentario the cod_imagen_comentario to set
     */
    public void setCod_imagen_comentario(String cod_imagen_comentario) {
        this.cod_imagen_comentario = cod_imagen_comentario;
    }
    //</editor-fold>

    /**
     * Permite convertir en String el objeto actual
     *
     * @return Cadena con la información de la lista
     */
    @Override
    public String toString() {
        return "comentario{" + "mensaje=" + getMensaje() + ", id_comentario=" + getId_comentario() + ", cod_perfil_comentario=" + getCod_perfil_comentario() + ", cod_imagen_comentario=" + getCod_imagen_comentario() + '}';
    }

}
