package mx.edu.utez.generaacionnomenclatura.dto;

public class Generacion {
    private int id;
    private int nombre;
    private String periodoMes;
    private int periodoAnio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setPeriodoMes(String peridoMes) {
        this.periodoMes = peridoMes;
    }

    public int getPeriodoAnio() {
        return periodoAnio;
    }

    public void setPeriodoAnio(int periodoAnio) {
        this.periodoAnio = periodoAnio;
    }

    public Generacion() {
    }

    public Generacion(int id, int nombre, String periodoMes, int periodoAnio) {
        this.id = id;
        this.nombre = nombre;
        this.periodoMes = periodoMes;
        this.periodoAnio = periodoAnio;
    }
}
