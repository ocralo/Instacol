package DataBase;


/**
 *
 * @author rodrigoescobarlopez
 */
public class ImagenPost extends Objeto {

    private Imagen imagen;
    private int id, meGusta, codImagenPerfil;

    public ImagenPost(Imagen imagen, int meGusta, int codImagenPerfil) {
        this.imagen = imagen;
        this.meGusta = meGusta;
        this.codImagenPerfil = codImagenPerfil;
    }

    public ImagenPost(Imagen imagen, int id, int meGusta, int codImagenPerfil) {
        this.imagen = imagen;
        this.id = id;
        this.meGusta = meGusta;
        this.codImagenPerfil = codImagenPerfil;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters && Setters">
    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMeGusta() {
        return meGusta;
    }

    public void setMeGusta(int meGusta) {
        this.meGusta = meGusta;
    }

    public int getCodImagenPerfil() {
        return codImagenPerfil;
    }

    public void setCodImagenPerfil(int codImagenPerfil) {
        this.codImagenPerfil = codImagenPerfil;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "imagen{\n"
                + "imagen=" + imagen + "\n"
                + "id=" + id + "\n"
                + "megusta=" + meGusta + "\n"
                + "codimagen_Perfil=" + codImagenPerfil + '}';
    }

    /**
     * Metodo que realiza un clonado de la imagen actual
     *
     * @return clon del objeto actual
     */
    @Override
    public Objeto clon() {
        return new ImagenPost(imagen, id, meGusta, codImagenPerfil);
    }

    /**
     * Metodo que permite comprar dos imagenes por su ID
     *
     * @param obj
     * @return returna 0 si son iguales, 1 si obj es menor o -1 si obj es mayor
     */
    @Override
    public int igual(Objeto obj) {
        ImagenPost i = (ImagenPost) obj;

        if (id > i.getId()) {
            return 1;
        } else if (id < i.getId()) {
            return -1;
        }
        return 0;
    }
}
