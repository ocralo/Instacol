package Modelo;

import java.awt.Image;
import javax.swing.ImageIcon;

public class imagen {

    private Image imagen;
    private String id_imagen;
    private String me_gusta;
    private String cod_perfil_imagen;
    private String ruta;

    /**
     * Permite crear una instancia de la clase Imagen que almancena una archivo
     * Image
     *
     * @param imagen - Clase image que permite almacenar imagenes en tiempo de
     * ejecucion
     * @param me_gusta - numero de "Me gusta" que posee la imagen
     * @param id_imagen - Identificador de la imagen (Debe proporcionarlo la
     * base de datos)
     * @param cod_perfil_imagen - Codigo del perfil que tiene asignada la imagen
     */
    public imagen(Image imagen, String me_gusta, String id_imagen, String cod_perfil_imagen) {
        this.imagen = imagen;
        this.me_gusta = me_gusta;
        this.id_imagen = id_imagen;
        this.cod_perfil_imagen = cod_perfil_imagen;
    }

    /**
     * Permite crear una imagen a partir de una ruta en el disco duro
     *
     * @param cod_perfil_imagen - Codigo del perfil que tiene asignada la imagen
     * @param ruta - Cadena con la direción en el disco de la imagen que se
     * desea crear
     */
    public imagen(String cod_perfil_imagen, String ruta) {
        this.cod_perfil_imagen = cod_perfil_imagen;
        this.ruta = ruta;
    }

    /**
     * Metodo que transforma un imagenbyte a ImageIcon
     *
     * @param imagenbyte - arregloque se desea comvertir en ImageIcon
     * @return El ImageIcon resultante del arreglo de byte
     */
    public static ImageIcon byteAImagen(byte[] imagenbyte) {
        ImageIcon imageIcon = new ImageIcon(imagenbyte);
        return imageIcon;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters && Setters">
    /**
     * @return the imagen
     */
    public Image getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the id_imagen
     */
    public String getId_imagen() {
        return id_imagen;
    }

    /**
     * @param id_imagen the id_imagen to set
     */
    public void setId_imagen(String id_imagen) {
        this.id_imagen = id_imagen;
    }

    /**
     * @return the me_gusta
     */
    public String getMe_gusta() {
        return me_gusta;
    }

    /**
     * @param me_gusta the me_gusta to set
     */
    public void setMe_gusta(String me_gusta) {
        this.me_gusta = me_gusta;
    }

    /**
     * @return the cod_perfil_imagen
     */
    public String getCod_perfil_imagen() {
        return cod_perfil_imagen;
    }

    /**
     * @param cod_perfil_imagen the cod_perfil_imagen to set
     */
    public void setCod_perfil_imagen(String cod_perfil_imagen) {
        this.cod_perfil_imagen = cod_perfil_imagen;
    }

    /**
     * @return the ruta
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
//</editor-fold>

    /**
     * Permite convertir en String el objeto actual
     *
     * @return Cadena con la información de la lista
     */
    @Override
    public String toString() {
        return "imagen{" + "imagen=" + getImagen() + ", me_gusta=" + getMe_gusta() + ", id_imagen=" + getId_imagen() + ", cod_perfil_imagen=" + getCod_perfil_imagen() + '}';
    }
}
