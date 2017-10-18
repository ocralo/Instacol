/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Image;

/**
 *
 * @author Momo
 */
public class perfil {
    
    private String nombre_perfil;
    private Image foto_perfil;
    private String id_perfil;
    private String cod_usuario;

    public perfil() {
    }

    public perfil(String nombre_perfil, Image foto_perfil, String id_perfil, String cod_usuario) {
        this.nombre_perfil = nombre_perfil;
        this.foto_perfil = foto_perfil;
        this.id_perfil = id_perfil;
        this.cod_usuario = cod_usuario;
    }
    
    public perfil(String nombre_perfil, Image foto_perfil, String cod_usuario) {
        this.nombre_perfil = nombre_perfil;
        this.foto_perfil = foto_perfil;
        this.cod_usuario = cod_usuario;
    }

    public String getNombre_perfil() {
        return nombre_perfil;
    }

    public Image getFoto_perfil() {
        return foto_perfil;
    }

    public String getId_perfil() {
        return id_perfil;
    }

    public String getCod_usuario() {
        return cod_usuario;
    }

    public void setNombre_perfil(String nombre_perfil) {
        this.nombre_perfil = nombre_perfil;
    }

    public void setFoto_perfil(Image foto_perfil) {
        this.foto_perfil = foto_perfil;
    }

    public void setId_perfil(String id_perfil) {
        this.id_perfil = id_perfil;
    }

    public void setCod_usuario(String cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    @Override
    public String toString() {
        return "perfil{" + "nombre_perfil=" + nombre_perfil + ", foto_perfil=" + foto_perfil + ", id_perfil=" + id_perfil + ", cod_usuario=" + cod_usuario + '}';
    }
    
    
    
}
