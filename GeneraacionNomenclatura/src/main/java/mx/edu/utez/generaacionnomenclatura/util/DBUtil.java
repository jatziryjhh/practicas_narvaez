package mx.edu.utez.generaacionnomenclatura.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtil {

    private static String url;
    private static String user;
    private static String password;

    static {
        try (InputStream input = DBUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            url = prop.getProperty("db.url");
            user = prop.getProperty("db.username");  // Asegúrate que coincide con tu db.properties
            password = prop.getProperty("db.password");

            // Test de conexión
            try (Connection c = DriverManager.getConnection(url, user, password)) {
                System.out.println("Conexión OK: " + c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new RuntimeException("Error al conectar a la base de datos", e);
        }
    }
}