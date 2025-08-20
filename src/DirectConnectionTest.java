import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DirectConnectionTest {
    public static void main(String[] args) {
        System.out.println("=== Direct Database Connection Test ===");

        // MySQL connection parameters
        String url = "jdbc:mysql://localhost:3306/billingdb?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "";

        Connection conn = null;
        try {
            // Load driver explicitly
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ Driver loaded successfully");

            // Create connection
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connected to database: " + conn.getCatalog());

            // Test query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM users");

            if (rs.next()) {
                System.out.println("✅ Users table exists with " + rs.getInt("count") + " records");
            }

            // List all tables
            System.out.println("\nTables in database:");
            rs = stmt.executeQuery("SHOW TABLES");
            while (rs.next()) {
                System.out.println(" - " + rs.getString(1));
            }

        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL Driver not found: " + e.getMessage());
            System.err.println("Download from: https://dev.mysql.com/downloads/connector/j/");
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("✅ Connection closed");
                } catch (Exception e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }
}