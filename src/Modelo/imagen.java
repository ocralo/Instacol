/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Momo
 */
public class imagen {
    
    private String imagen;
    private String me_gusta;
    private String id_imagen;
    private String cod_perfil_imagen;

    public imagen() {
    }

    public imagen(String imagen, String me_gusta, String id_imagen, String cod_perfil_imagen) {
        this.imagen = imagen;
        this.me_gusta = me_gusta;
        this.id_imagen = id_imagen;
        this.cod_perfil_imagen = cod_perfil_imagen;
    }

    public String getImagen() {
        return imagen;
    }

    public String getMe_gusta() {
        return me_gusta;
    }

    public String getId_imagen() {
        return id_imagen;
    }

    public String getCod_perfil_imagen() {
        return cod_perfil_imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setMe_gusta(String me_gusta) {
        this.me_gusta = me_gusta;
    }

    public void setId_imagen(String id_imagen) {
        this.id_imagen = id_imagen;
    }

    public void setCod_perfil_imagen(String cod_perfil_imagen) {
        this.cod_perfil_imagen = cod_perfil_imagen;
    }

    @Override
    public String toString() {
        return "imagen{" + "imagen=" + imagen + ", me_gusta=" + me_gusta + ", id_imagen=" + id_imagen + ", cod_perfil_imagen=" + cod_perfil_imagen + '}';
    }
    
    
    
}
