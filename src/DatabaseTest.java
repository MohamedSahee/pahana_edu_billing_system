import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DatabaseTest extends HttpServlet {
    public static void main(String[] args) {
        System.out.println("=== Simple Database Connection Test ===");

        Connection conn = null;
        try {
            // Load driver manually
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create connection directly
            String url = "jdbc:mysql://localhost:3306/billingdb?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String password = "";

            conn = DriverManager.getConnection(url, user, password);

            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Connected to: " + meta.getDatabaseProductName() + " " + meta.getDatabaseProductVersion());

                // Check if our tables exist
                String[] tableTypes = {"TABLE"};
                ResultSet tables = meta.getTables("billingdb", null, "%", tableTypes);

                System.out.println("\nTables in billingdb:");
                while (tables.next()) {
                    String tableName = tables.getString("TABLE_NAME");
                    System.out.println(" - " + tableName);
                }
                tables.close();

                System.out.println("\n✅ Database setup is correct!");
            } else {
                System.out.println("❌ Failed to establish database connection");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }
}