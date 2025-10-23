package mx.edu.utez.nomenclaturas.model.dao;
import mx.edu.utez.nomenclaturas.model.bean.UsuarioBean;
import mx.edu.utez.nomenclaturas.util.MysqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDao {

    public UsuarioBean validarUsuario(String username, String password) {
        String sql = "SELECT * FROM usuario WHERE username=? AND password=?";
        try (Connection con = new MysqlConector().connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new UsuarioBean(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
