package mx.edu.utez.generaacionnomenclatura.dto;

public class Nomenclatura {
    private int id;
    private int valorNumerico;
    private String descripcion;
    private int idGeneracion;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public Nomenclatura() {
    }
    public Nomenclatura(int id, int valorNumerico, String descripcion, int idGeneracion) {
        this.id = id;
        this.valorNumerico = valorNumerico;
        this.descripcion = descripcion;
        this.idGeneracion = idGeneracion;
    }
}
