package mx.edu.utez.nomenclaturas.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConector {
    private final String DBNAME = "generacion_nomenclatura";
    private final String USER = "root";
    private final String PASSWORD = "root"; // cámbialo según tu MySQL
    private final String TIMEZONE = "America/Mexico_City";
    private final String USESSL = "false";
    private final String PUBLICKEY = "true";
    private final String DDLAUTO = "true";
    private final String HOST = "localhost";

    public Connection connect() {
        String dataSource = String.format(
                "jdbc:mysql://%s:3306/%s?user=%s&password=%s&serverTimezone=%s&useSSL=%s&allowPublicKeyRetrieval=%s&createDatabaseIfNotExist=%s",
                HOST, DBNAME, USER, PASSWORD, TIMEZONE, USESSL, PUBLICKEY, DDLAUTO);

        try {
            // Ya no es necesario registrar explícitamente el Driver en versiones modernas,
            // pero lo dejamos por compatibilidad
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(dataSource);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try (Connection conn = new MysqlConector().connect()) {
            if (conn != null) {
                System.out.println("✅ Conexión realizada");
            } else {
                System.out.println("❌ Conexión fallida");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
