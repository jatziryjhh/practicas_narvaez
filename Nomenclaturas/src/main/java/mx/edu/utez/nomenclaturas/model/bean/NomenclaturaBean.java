package mx.edu.utez.nomenclaturas.model.bean;
public class NomenclaturaBean {
    private int idNomenclatura;
    private int valorNumerico;    // Ej. 7, 8, 9, 10
    private String descripcion;   // Ej. NA, SA, DE, AU
    private int idGeneracion;     // Relación con la generación
    private boolean status;       // Activo (true) / Inactivo (false)

    // Constructor vacío
    public NomenclaturaBean() {}

    // Constructor completo
    public NomenclaturaBean(int idNomenclatura, int valorNumerico, String descripcion, int idGeneracion, boolean status) {
        this.idNomenclatura = idNomenclatura;
        this.valorNumerico = valorNumerico;
        this.descripcion = descripcion;
        this.idGeneracion = idGeneracion;
        this.status = status;
    }

    // Getters y Setters
    public int getIdNomenclatura() {
        return idNomenclatura;
    }

    public void setIdNomenclatura(int idNomenclatura) {
        this.idNomenclatura = idNomenclatura;
    }

    public int getValorNumerico() {
        return valorNumerico;
    }

    public void setValorNumerico(int valorNumerico) {
        this.valorNumerico = valorNumerico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdGeneracion() {
        return idGeneracion;
    }

    public void setIdGeneracion(int idGeneracion) {
        this.idGeneracion = idGeneracion;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "NomenclaturaBean{" +
                "idNomenclatura=" + idNomenclatura +
                ", valorNumerico=" + valorNumerico +
                ", descripcion='" + descripcion + '\'' +
                ", idGeneracion=" + idGeneracion +
                ", status=" + status +
                '}';
    }
}
