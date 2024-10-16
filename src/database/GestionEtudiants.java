package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestionEtudiants {
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_etudiants";
    private static final String USER = "root";
    private static final String PASSWORD = "davou64598258";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
