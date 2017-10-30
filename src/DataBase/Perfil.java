package DataBase;


/**
 *
 * @author Usaka Rokujou
 */
public class Perfil extends Objeto {

    String nombrePerfil;
    int idPerfil, codUsuario;
    Imagen fotoPerfil;

    public Perfil(String nombrePerfil, int codUsuario) {
        this.nombrePerfil = nombrePerfil;
        this.codUsuario = codUsuario;
    }

    public Perfil(String nombrePerfil, int codUsuario, Imagen fotoPerfil) {
        this.nombrePerfil = nombrePerfil;
        this.codUsuario = codUsuario;
        this.fotoPerfil = fotoPerfil;
    }

    public Perfil(String nombrePerfil, int idPerfil, int codUsuario, Imagen fotoPerfil) {
        this.nombrePerfil = nombrePerfil;
        this.idPerfil = idPerfil;
        this.codUsuario = codUsuario;
        this.fotoPerfil = fotoPerfil;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters && Setters">
    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Imagen getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(Imagen fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
//</editor-fold>

    @Override
    public String toString() {
        return "Perfil{\n"
                + "nombrePerfil=" + nombrePerfil + "\n"
                + "idPerfil=" + idPerfil + "\n"
                + "codUsuario=" + codUsuario + "\n"
                + "fotoPerfil=" + fotoPerfil + '}';
    }

    /**
     * Metodo que realiza un clonado del perfil actual con foto
     *
     * @return clon del objeto actual
     */
    @Override
    public Objeto clon() {
        return new Perfil(nombrePerfil, idPerfil, codUsuario, fotoPerfil);
    }

    /**
     * Metodo que permite comprar dos perfiles por su id
     *
     * @param obj
     * @return returna 0 si son iguales, 1 si obj es menor o -1 si obj es mayor
     */
    @Override
    public int igual(Objeto obj) {
        Perfil p = (Perfil) obj;

        if (idPerfil > p.getIdPerfil()) {
            return 1;
        } else if (idPerfil < p.getIdPerfil()) {
            return -1;
        }
        return 0;
    }
}
