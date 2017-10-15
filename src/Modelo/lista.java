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
public class lista {
    
    private String nombre_lista;
    private String id_lista;
    private String cod_perfil_lista;

    public lista() {
    }

    public lista(String nombre_lista, String id_lista, String cod_perfil_lista) {
        this.nombre_lista = nombre_lista;
        this.id_lista = id_lista;
        this.cod_perfil_lista = cod_perfil_lista;
    }

    public String getNombre_lista() {
        return nombre_lista;
    }

    public String getId_lista() {
        return id_lista;
    }

    public String getCod_perfil_lista() {
        return cod_perfil_lista;
    }

    public void setNombre_lista(String nombre_lista) {
        this.nombre_lista = nombre_lista;
    }

    public void setId_lista(String id_lista) {
        this.id_lista = id_lista;
    }

    public void setCod_perfil_lista(String cod_perfil_lista) {
        this.cod_perfil_lista = cod_perfil_lista;
    }

    @Override
    public String toString() {
        return "lista{" + "nombre_lista=" + nombre_lista + ", id_lista=" + id_lista + ", cod_perfil_lista=" + cod_perfil_lista + '}';
    }
    
    
    
}
