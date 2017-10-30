package DontTouch;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author Usaka Rokujou
 */
public class Tools {

    /**
     * Permite imprimir mensajes en un JOptionPane
     *
     * @param mensaje
     */
    public static void imprimirJ(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    /**
     * Permite imprimri mensajes en la consola
     *
     * @param mensaje
     */
    public static void imprimirC(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Permite realizar un ordenamiento de elementos que extiendan de Base
     *
     * @param objetos
     * @param metodo - puede ser Asc o Desc
     * @return Retorna la lista de elementos ordenados de acuerdo al parametro
     * metodo
     */
    public static LinkedList<Objeto> ordenamiento(LinkedList<Objeto> objetos, String metodo) {
        return objetos;
    }

    /**
     * Metodo que permite realizar la busqueda de un elemento de una lista
     *
     * @param objetos - Lista de elementos donde se desea realizar la buscar
     * @param b - Elemento que se deseabsucar en la lista
     * @return Elemento encontrado en la lista, o null si no existe
     */
    public static Objeto busqueda(LinkedList<Objeto> objetos, Objeto b) {

        return b;
    }

    /**
     * Permite convertir archivos File a binario
     *
     * @param archivo Archivo que se desea convertir
     * @return Arreglo de byte con la lista de bytes del archivo
     */
    public byte[] convertirImagenAByte(File archivo) {

        try {
            BufferedImage bufferedImage = ImageIO.read(archivo);

            WritableRaster raster = bufferedImage.getRaster();
            DataBufferByte data = (DataBufferByte) raster.getDataBuffer();

            return (data.getData());
        } catch (IOException ex) {
            imprimirC(ex.getMessage());
        }

        return null;
    }

    //<editor-fold defaultstate="collapsed" desc="Metodos Recursivos">
    private static LinkedList<Objeto> ordenamientoAsc(LinkedList<Objeto> elementos) {
        return elementos;
    }

    private static LinkedList<Objeto> ordenamientoDesc(LinkedList<Objeto> elementos) {
        return elementos;
    }

    private static void busquedaRec() {

    }
//</editor-fold>

}
