package mx.edu.utez.nomenclaturas.model.dao;

import mx.edu.utez.nomenclaturas.model.bean.NomenclaturaBean;
import mx.edu.utez.nomenclaturas.util.MysqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NomenclaturaDao {

    // Listar nomenclaturas de una generación
    public List<NomenclaturaBean> findByGeneracion(int idGeneracion) {
        List<NomenclaturaBean> lista = new ArrayList<>();
        String sql = "SELECT * FROM nomenclatura WHERE id_generacion=? ORDER BY valor_numerico ASC";

        try (Connection con = new MysqlConector().connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idGeneracion);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                NomenclaturaBean n = new NomenclaturaBean();
                n.setIdNomenclatura(rs.getInt("id_nomenclatura"));
                n.setValorNumerico(rs.getInt("valor_numerico"));
                n.setDescripcion(rs.getString("descripcion"));
                n.setIdGeneracion(rs.getInt("id_generacion"));
                n.setStatus(rs.getBoolean("status"));
                lista.add(n);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Insertar nueva nomenclatura
    public boolean insert(NomenclaturaBean n) {
        String sql = "INSERT INTO nomenclatura(valor_numerico, descripcion, id_generacion, status) VALUES(?,?,?,?)";
        try (Connection con = new MysqlConector().connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, n.getValorNumerico());
            ps.setString(2, n.getDescripcion());
            ps.setInt(3, n.getIdGeneracion());
            ps.setBoolean(4, n.isStatus());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Actualizar nomenclatura
    public boolean update(NomenclaturaBean n) {
        String sql = "UPDATE nomenclatura SET valor_numerico=?, descripcion=?, status=? WHERE id_nomenclatura=?";
        try (Connection con = new MysqlConector().connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, n.getValorNumerico());
            ps.setString(2, n.getDescripcion());
            ps.setBoolean(3, n.isStatus());
            ps.setInt(4, n.getIdNomenclatura());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Activar/desactivar nomenclatura
    public boolean toggleStatus(int id, boolean status) {
        String sql = "UPDATE nomenclatura SET status=? WHERE id_nomenclatura=?";
        try (Connection con = new MysqlConector().connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setBoolean(1, status);
            ps.setInt(2, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Buscar nomenclatura por ID
    public NomenclaturaBean findById(int id) {
        String sql = "SELECT * FROM nomenclatura WHERE id_nomenclatura=?";
        try (Connection con = new MysqlConector().connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new NomenclaturaBean(
                        rs.getInt("id_nomenclatura"),
                        rs.getInt("valor_numerico"),
                        rs.getString("descripcion"),
                        rs.getInt("id_generacion"),
                        rs.getBoolean("status")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public NomenclaturaBean obtenerPorId(int idNomenclatura) {
        return findById(idNomenclatura);
    }
}
