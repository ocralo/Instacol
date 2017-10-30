package DontTouch;

/**
 *
 * @author Usaka Rokujou
 */
public class PruebasDB {

    public static void main(String... arg) {
        new PruebasDB().insertarComen();
    }

    //<editor-fold defaultstate="collapsed" desc="Pruebas de insercion">
    public void insertarUsuario() {
        Tools.imprimirC("" + DontTouch.Insercion.sqlInsertUsuario(new Usuario("Breyner", "Albarracin", "basfv@sfitvbrs", "jaja", "1997/09/09")));
    }

    public void insertarPerfil() {
        Imagen img = new Imagen("C:\\Users\\breyn\\Desktop\\Jacob_Frye.jpg");
        Perfil p = new Perfil("UsakaRKJ", 4, img);

        Tools.imprimirC("" + DontTouch.Insercion.sqlInsertPerfilIMG(p));
    }

    public void insertarIma() {
        Imagen img = new Imagen("C:\\Users\\breyn\\Desktop\\Jacob_Frye.jpg");

        Tools.imprimirC("" + DontTouch.Insercion.sqlInsertImagen(new ImagenPost(img, 0, 1)));

    }

    public void insertarComen() {
        Tools.imprimirC("" + DontTouch.Insercion.sqlInsertComentario(new Comentario("FUNCIONA!!!!", 1, 1)));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Pruebas de Eliminacion">
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Pruebas de Busqueda">
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Pruebas de Actualizacion">
//</editor-fold>
}
