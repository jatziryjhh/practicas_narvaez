package mx.edu.utez.generaacionnomenclatura.dao;
import mx.edu.utez.generaacionnomenclatura.dto.Nomenclatura;
import mx.edu.utez.generaacionnomenclatura.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NomenclaturaDAOImpl extends NomenclaturaDAO {

    @Override
    public List<Nomenclatura> getByGeneracion(int idGeneracion) throws Exception {
        List<Nomenclatura> list = new ArrayList<>();
        String sql = "SELECT id_nomenclatura, valor_numerico, descripcion, id_generacion FROM nomenclatura WHERE id_generacion = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idGeneracion);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Nomenclatura n = new Nomenclatura();
                    n.setId(rs.getInt("id_nomenclatura"));
                    n.setValorNumerico(rs.getInt("valor_numerico"));
                    n.setDescripcion(rs.getString("descripcion"));
                    n.setIdGeneracion(rs.getInt("id_generacion"));
                    list.add(n);
                }
            }
        }
        return list;
    }

    @Override
    public void add(Nomenclatura n) throws Exception {
        String sql = "INSERT INTO nomenclatura (valor_numerico, descripcion, id_generacion) VALUES (?, ?, ?)";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, n.getValorNumerico());
            ps.setString(2, n.getDescripcion());
            ps.setInt(3, n.getIdGeneracion());
            ps.executeUpdate();
        }
    }

    @Override
    public void update(Nomenclatura n) throws Exception {
        String sql = "UPDATE nomenclatura SET valor_numerico = ?, descripcion = ? WHERE id_nomenclatura = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, n.getValorNumerico());
            ps.setString(2, n.getDescripcion());
            ps.setInt(3, n.getId());
            ps.executeUpdate();
        }
    }
}

