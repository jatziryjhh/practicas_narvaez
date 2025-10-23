package mx.edu.utez.generaacionnomenclatura.dao;

import mx.edu.utez.generaacionnomenclatura.dto.Generacion;

import java.util.List;

public abstract class GeneracionDAO {
    public abstract List<Generacion> getAll() throws Exception;
}
