package DontTouch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Usaka Rokujou
 */
public class Conexion {

    private static Connection conexion;
    private static Statement st;

    /**
     * Constructor fundamental de la clase
     */
    public Conexion() {
    }

    /**
     * Método utilizado para establecer la conexión con la base de datos
     * instacol
     *
     * @return estado regresa el estado de la conexión, true si se estableció la
     * conexión, falso en caso contrario
     */
    public static boolean crearConexion() {
        try {
            String usuario = "root";
            String clave = "Usaka.mysql2804";
            String bd = "instacol";
            Class.forName("com.mysql.jdbc.Driver");                                      //user  //pass
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + bd, usuario, clave);
            st = conexion.createStatement();
        } catch (SQLException | ClassNotFoundException ex) {
            Tools.imprimirC(ex.getMessage());
            return false;
        }
        return true;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters && Setters">
    public static Connection getConexion() {
        return conexion;
    }

    public static Statement getSt() {
        return st;
    }
    //</editor-fold>
}
