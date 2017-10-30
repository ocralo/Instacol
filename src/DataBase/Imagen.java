package DataBase;

import java.awt.Image;
import java.io.File;

/**
 *
 * @author Usaka Rokujou
 */
public class Imagen {

    private String ruta;
    private File archivo;
    Image imagen;

    public Imagen(String ruta) {
        this.ruta = ruta;
        this.archivo = new File(ruta);
    }

    public Imagen(Image imagen) {
        this.imagen = imagen;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters && Setters">
    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
//</editor-fold>
}
