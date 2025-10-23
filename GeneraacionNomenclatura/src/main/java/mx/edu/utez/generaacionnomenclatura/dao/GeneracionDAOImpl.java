package mx.edu.utez.generaacionnomenclatura.dao;

import mx.edu.utez.generaacionnomenclatura.dto.Generacion;
import mx.edu.utez.generaacionnomenclatura.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GeneracionDAOImpl extends GeneracionDAO {
    @Override
    public List<Generacion> getAll() throws Exception {
        List<Generacion> list = new ArrayList<>();
        String sql = "SELECT id_generacion, nombre, periodo_mes, periodo_anio FROM generacion";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                // Debug: imprime cada fila que encuentra
                System.out.println("Generacion encontrada: " + rs.getInt("nombre"));

                Generacion g = new Generacion();
                g.setId(rs.getInt("id_generacion"));
                g.setNombre(rs.getInt("nombre"));
                g.setPeriodoMes(rs.getString("periodo_mes"));
                g.setPeriodoAnio(rs.getInt("periodo_anio"));
                list.add(g);
            }
        }
        return list;
    }
}
