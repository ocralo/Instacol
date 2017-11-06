/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Control.BaseDatos;
import java.awt.Image;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.ImageIcon;

/**
 *
 * @author Momo
 */
public class imagen {

    private Image imagen;
    private String id_imagen;
    private String me_gusta;
    private String cod_perfil_imagen;
    private String ruta;

    public imagen() {
    }

    public imagen(Image imagen,String me_gusta, String id_imagen, String cod_perfil_imagen) {
        this.imagen = imagen;
        this.me_gusta = me_gusta;
        this.id_imagen = id_imagen;
        this.cod_perfil_imagen = cod_perfil_imagen;
    }

    public imagen( String cod_perfil_imagen, String ruta) {
        this.cod_perfil_imagen = cod_perfil_imagen;
        this.ruta = ruta;
    }

    public Image getImagen() {
        return imagen;
    }

    public String getId_imagen() {
        return id_imagen;
    }

    public String getCod_perfil_imagen() {
        return cod_perfil_imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public void setId_imagen(String id_imagen) {
        this.id_imagen = id_imagen;
    }

    public void setCod_perfil_imagen(String cod_perfil_imagen) {
        this.cod_perfil_imagen = cod_perfil_imagen;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getMe_gusta() {
        return me_gusta;
    }

    public void setMe_gusta(String me_gusta) {
        this.me_gusta = me_gusta;
    }

    @Override
    public String toString() {
        return "imagen{" + "imagen=" + imagen +", me_gusta="+me_gusta+ ", id_imagen=" + id_imagen + ", cod_perfil_imagen=" + cod_perfil_imagen + '}';
    }

//    public boolean insertarImagen(LinkedList<imagen> imagen, String ruta) throws SQLException, FileNotFoundException {
//
//        String sql;
//        BaseDatos objBases = new BaseDatos();
//        boolean conexion;
//        boolean insertar = false;
//
//        sql = "insert into imagen "
//                + "(imagen,me_gusta_imagen,id_imagen,cod_perfil_imagen) values(?,?,?,?)";
//        for (imagen imagen1 : imagen) {
//            conexion = objBases.crearConexion();
//            if (conexion) {
//                insertar = objBases.sqlInsertImagen(sql,
//                        imagen1.getImagen(),
//                        imagen1.getMe_gusta(),
//                        imagen1.getId_imagen(),
//                        imagen1.getCod_perfil_imagen());
//            }
//        }
//        return insertar;
//    }

    public static ImageIcon byteAImagen(byte[] imagenbyte) {
        ImageIcon imageIcon = new ImageIcon(imagenbyte);
        return imageIcon;
    }

}
