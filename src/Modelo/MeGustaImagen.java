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
public class MeGustaImagen {
    private String id_meGusta;
    private String me_gusta_imagen;
    private String cod_meGusta_imagen;
    private String cod_meGusta_perfil;

    public MeGustaImagen() {
    }

    public MeGustaImagen(String id_meGusta, String me_gusta_imagen, String cod_meGusta_imagen, String cod_meGusta_perfil) {
        this.id_meGusta = id_meGusta;
        this.me_gusta_imagen = me_gusta_imagen;
        this.cod_meGusta_imagen = cod_meGusta_imagen;
        this.cod_meGusta_perfil = cod_meGusta_perfil;
    }

    public String getId_meGusta() {
        return id_meGusta;
    }

    public void setId_meGusta(String id_meGusta) {
        this.id_meGusta = id_meGusta;
    }

    public String getMe_gusta_imagen() {
        return me_gusta_imagen;
    }

    public void setMe_gusta_imagen(String me_gusta_imagen) {
        this.me_gusta_imagen = me_gusta_imagen;
    }

    public String getCod_meGusta_imagen() {
        return cod_meGusta_imagen;
    }

    public void setCod_meGusta_imagen(String cod_meGusta_imagen) {
        this.cod_meGusta_imagen = cod_meGusta_imagen;
    }

    public String getCod_meGusta_perfil() {
        return cod_meGusta_perfil;
    }

    public void setCod_meGusta_perfil(String cod_meGusta_perfil) {
        this.cod_meGusta_perfil = cod_meGusta_perfil;
    }

    @Override
    public String toString() {
        return "MeGustaImagen{" + "id_meGusta=" + id_meGusta + ", me_gusta_imagen=" + me_gusta_imagen + ", cod_meGusta_imagen=" + cod_meGusta_imagen + ", cod_meGusta_perfil=" +cod_meGusta_perfil+'}';
    }
}
