package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL  = System.getProperty("db.url",  "jdbc:mysql://localhost:3306/online_billing?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
    private static final String USER = System.getProperty("db.user", "root");
    private static final String PASS = System.getProperty("db.pass", "");

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
