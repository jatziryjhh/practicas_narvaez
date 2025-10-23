package mx.edu.utez.nomenclaturas.model.dao;
import mx.edu.utez.nomenclaturas.model.bean.GeneracionBean;
import mx.edu.utez.nomenclaturas.util.MysqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GeneracionDao {

    // Listar todas las generaciones
    public List<GeneracionBean> findAll() {
        List<GeneracionBean> lista = new ArrayList<>();
        String sql = "SELECT * FROM generacion ORDER BY nombre ASC";

        try (Connection con = new MysqlConector().connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                GeneracionBean g = new GeneracionBean();
                g.setIdGeneracion(rs.getInt("id_generacion"));
                g.setNombre(rs.getInt("nombre"));
                g.setPeriodoMes(rs.getString("periodo_mes"));
                g.setPeriodoAnio(rs.getInt("periodo_anio"));
                lista.add(g);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Buscar una generación por ID
    public GeneracionBean findById(int id) {
        String sql = "SELECT * FROM generacion WHERE id_generacion=?";
        try (Connection con = new MysqlConector().connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new GeneracionBean(
                        rs.getInt("id_generacion"),
                        rs.getInt("nombre"),
                        rs.getString("periodo_mes"),
                        rs.getInt("periodo_anio")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
