import dao.UserDAO;
import model.User;

public class UserTest {
    public static void main(String[] args) {
        System.out.println("=== USER MANAGEMENT SYSTEM TEST ===");

        UserDAO userDAO = new UserDAOImpl();

        // Test 1: Get all users
        System.out.println("\n1. All Users:");
        userDAO.getAllUsers().forEach(System.out::println);

        // Test 2: Get user by username
        System.out.println("\n2. User by username 'admin':");
        User admin = userDAO.getUserByUsername("admin");
        System.out.println(admin);

        // Test 3: Validate credentials
        System.out.println("\n3. Credential validation:");
        System.out.println("admin/admin123: " + userDAO.validateCredentials("admin", "admin123"));
        System.out.println("admin/wrongpass: " + userDAO.validateCredentials("admin", "wrongpass"));

        // Test 4: Count users
        System.out.println("\n4. User counts:");
        System.out.println("Total users: " + userDAO.getTotalUserCount());
        System.out.println("Active users: " + userDAO.getActiveUserCount());
        System.out.println("Admin users: " + userDAO.getUsersCountByRole("admin"));

        // Test 5: Check existence
        System.out.println("\n5. Existence checks:");
        System.out.println("Username 'admin' exists: " + userDAO.usernameExists("admin"));
        System.out.println("Email 'admin@billing.com' exists: " + userDAO.emailExists("admin@billing.com"));

        System.out.println("\n✅ All tests completed!");
    }
}