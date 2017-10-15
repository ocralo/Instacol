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
public class comentario {
    
    private String mensaje;
    private String id_comentario;
    private String cod_perfil_comentario;
    private String cod_imagen_comentario;

    public comentario() {
    }

    public comentario(String mensaje, String id_comentario, String cod_perfil_comentario, String cod_imagen_comentario) {
        this.mensaje = mensaje;
        this.id_comentario = id_comentario;
        this.cod_perfil_comentario = cod_perfil_comentario;
        this.cod_imagen_comentario = cod_imagen_comentario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getId_comentario() {
        return id_comentario;
    }

    public String getCod_perfil_comentario() {
        return cod_perfil_comentario;
    }

    public String getCod_imagen_comentario() {
        return cod_imagen_comentario;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setId_comentario(String id_comentario) {
        this.id_comentario = id_comentario;
    }

    public void setCod_perfil_comentario(String cod_perfil_comentario) {
        this.cod_perfil_comentario = cod_perfil_comentario;
    }

    public void setCod_imagen_comentario(String cod_imagen_comentario) {
        this.cod_imagen_comentario = cod_imagen_comentario;
    }

    @Override
    public String toString() {
        return "comentario{" + "mensaje=" + mensaje + ", id_comentario=" + id_comentario + ", cod_perfil_comentario=" + cod_perfil_comentario + ", cod_imagen_comentario=" + cod_imagen_comentario + '}';
    }
    
    
    
}
