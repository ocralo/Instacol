package Modelo;

import java.awt.Image;

public class Perfil {

    private String nombre_perfil;
    private Image foto_perfil;
    private String id_perfil;
    private String cod_usuario;

    /**
     * Permite crear una instancia de un perfil asignado previamente a un
     * usuario
     *
     * @param nombre_perfil - nombre del perfil a observar
     * @param foto_perfil - foto del perfil que se mostrara en la app
     * @param id_perfil - Identificador del perfil (debe proporcionarlo la base
     * de datos)
     * @param cod_usuario - codigo del usuario que contiene este perfil
     */
    public Perfil(String nombre_perfil, Image foto_perfil, String id_perfil, String cod_usuario) {
        this.nombre_perfil = nombre_perfil;
        this.foto_perfil = foto_perfil;
        this.id_perfil = id_perfil;
        this.cod_usuario = cod_usuario;
    }

    /**
     * Permite crear una instancia de un perfil asignado previamente a un
     * usuario
     *
     * @param nombre_perfil - nombre del perfil a observar
     * @param foto_perfil - foto del perfil que se mostrara en la app
     * @param cod_usuario - codigo del usuario que contiene este perfil
     */
    public Perfil(String nombre_perfil, Image foto_perfil, String cod_usuario) {
        this.nombre_perfil = nombre_perfil;
        this.foto_perfil = foto_perfil;
        this.cod_usuario = cod_usuario;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters && Setter">
    /**
     * @return the nombre_perfil
     */
    public String getNombre_perfil() {
        return nombre_perfil;
    }

    /**
     * @param nombre_perfil the nombre_perfil to set
     */
    public void setNombre_perfil(String nombre_perfil) {
        this.nombre_perfil = nombre_perfil;
    }

    /**
     * @return the foto_perfil
     */
    public Image getFoto_perfil() {
        return foto_perfil;
    }

    /**
     * @param foto_perfil the foto_perfil to set
     */
    public void setFoto_perfil(Image foto_perfil) {
        this.foto_perfil = foto_perfil;
    }

    /**
     * @return the id_perfil
     */
    public String getId_perfil() {
        return id_perfil;
    }

    /**
     * @param id_perfil the id_perfil to set
     */
    public void setId_perfil(String id_perfil) {
        this.id_perfil = id_perfil;
    }

    /**
     * @return the cod_usuario
     */
    public String getCod_usuario() {
        return cod_usuario;
    }

    /**
     * @param cod_usuario the cod_usuario to set
     */
    public void setCod_usuario(String cod_usuario) {
        this.cod_usuario = cod_usuario;
    }
    //</editor-fold>

    /**
     * Permite convertir en String el objeto actual
     *
     * @return Cadena con la información de la lista
     */
    @Override
    public String toString() {
        return "perfil{" + "nombre_perfil=" + getNombre_perfil() + ", foto_perfil=" + getFoto_perfil() + ", id_perfil=" + getId_perfil() + ", cod_usuario=" + getCod_usuario() + '}';
    }

}
