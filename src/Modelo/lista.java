package Modelo;

public class lista {

    private String nombre_lista;
    private String id_lista;
    private String cod_perfil_lista;

    /**
     * Permite crear una instancia del objeto Lista
     *
     * @param nombre_lista - nombre que se desea asignar a la lista
     * @param id_lista - iD de identificacion (debe ser proporcionado por labase
     * de datos)
     * @param cod_perfil_lista - codigo que permite asignar la lista a un perfil
     */
    public lista(String nombre_lista, String id_lista, String cod_perfil_lista) {
        this.nombre_lista = nombre_lista;
        this.id_lista = id_lista;
        this.cod_perfil_lista = cod_perfil_lista;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters && Setter">
    /**
     * Retorna el nombre de la lista
     *
     * @return nombre_lista
     */
    public String getNombre_lista() {
        return nombre_lista;
    }

    /**
     * Permite modificar el nomrbe de la lista
     *
     * @param nombre_lista the nombre_lista to set
     */
    public void setNombre_lista(String nombre_lista) {
        this.nombre_lista = nombre_lista;
    }

    /**
     * Permite obtener el ID de la lista
     *
     * @return the id_lista
     */
    public String getId_lista() {
        return id_lista;
    }

    /**
     * Permite modificar el id de la lista
     *
     * @param id_lista the id_lista to set
     */
    public void setId_lista(String id_lista) {
        this.id_lista = id_lista;
    }

    /**
     * Permite obtener el codigo del perfil de la lista
     *
     * @return the cod_perfil_lista
     */
    public String getCod_perfil_lista() {
        return cod_perfil_lista;
    }

    /**
     * Permite modificar el codigo del perfil de la lista
     *
     * @param cod_perfil_lista the cod_perfil_lista to set
     */
    public void setCod_perfil_lista(String cod_perfil_lista) {
        this.cod_perfil_lista = cod_perfil_lista;
    }
    //</editor-fold>

    /**
     * Permite convertir en String el objeto actual
     *
     * @return Cadena con la información de la lista
     */
    @Override
    public String toString() {
        return "lista{" + "nombre_lista=" + getNombre_lista() + ", id_lista=" + getId_lista() + ", cod_perfil_lista=" + getCod_perfil_lista() + '}';
    }
}
