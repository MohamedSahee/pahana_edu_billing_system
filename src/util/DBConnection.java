package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Update these values according to your MySQL setup
    private static final String URL = "jdbc:mysql://localhost:3306/billingdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root"; // Your MySQL username
    private static final String PASSWORD = ""; // Your MySQL password

    // Static block to load the driver
    static {
        try {
            // Try to load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL JDBC Driver loaded successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL JDBC Driver not found. Make sure the MySQL connector JAR is in your classpath.");
            System.err.println("Error: " + e.getMessage());
            // Show where to look for the driver
            System.err.println("Please download from: https://dev.mysql.com/downloads/connector/j/");
            System.err.println("And place it in: WebContent/WEB-INF/lib/");
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Database connection successful!");
        } catch (SQLException e) {
            System.err.println("❌ Database connection failed: " + e.getMessage());
            System.err.println("URL: " + URL);
            System.err.println("User: " + USER);
            // Don't print password for security
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("✅ Database connection closed.");
            } catch (SQLException e) {
                System.err.println("❌ Error closing connection: " + e.getMessage());
            }
        }
    }

    // Test method
    public static void testConnection() {
        System.out.println("=== Testing Database Connection ===");
        System.out.println("URL: " + URL);
        System.out.println("User: " + USER);

        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("✅ Connection test successful!");
            closeConnection(conn);
        } else {
            System.out.println("❌ Connection test failed!");
        }
    }
}