package mx.edu.utez.nomenclaturas.model.bean;

public class GeneracionBean {
    private int idGeneracion;
    private int nombre;          // número de generación (ej. 2020)
    private String periodoMes;   // Ej. "Enero-Abril"
    private int periodoAnio;     // Ej. 2020

    // Constructor vacío
    public GeneracionBean() {}

    // Constructor completo
    public GeneracionBean(int idGeneracion, int nombre, String periodoMes, int periodoAnio) {
        this.idGeneracion = idGeneracion;
        this.nombre = nombre;
        this.periodoMes = periodoMes;
        this.periodoAnio = periodoAnio;
    }

    // Getters y Setters
    public int getIdGeneracion() {
        return idGeneracion;
    }

    public void setIdGeneracion(int idGeneracion) {
        this.idGeneracion = idGeneracion;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public String getPeriodoMes() {
        return periodoMes;
    }

    public void setPeriodoMes(String periodoMes) {
        this.periodoMes = periodoMes;
    }

    public int getPeriodoAnio() {
        return periodoAnio;
    }

    public void setPeriodoAnio(int periodoAnio) {
        this.periodoAnio = periodoAnio;
    }

    @Override
    public String toString() {
        return "GeneracionBean{" +
                "idGeneracion=" + idGeneracion +
                ", nombre=" + nombre +
                ", periodoMes='" + periodoMes + '\'' +
                ", periodoAnio=" + periodoAnio +
                '}';
    }
}
