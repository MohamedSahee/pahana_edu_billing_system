package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import dao.UserDAO;
import dao.UserDAOImpl;

public class UserManagementServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Check if user is logged in and is an admin
        if (user == null || !"admin".equals(user.getRole())) {
            response.sendRedirect("index.jsp");
            return;
        }

        UserDAO userDAO = new UserDAOImpl();
        java.util.List<User> users = userDAO.getAllUsers();

        request.setAttribute("users", users);
        request.getRequestDispatcher("manageUsers.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");

        // Check if user is logged in and is an admin
        if (currentUser == null || !"admin".equals(currentUser.getRole())) {
            response.sendRedirect("index.jsp");
            return;
        }

        String action = request.getParameter("action");
        UserDAO userDAO = new UserDAOImpl();

        if ("add".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String role = request.getParameter("role");

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(role);

            userDAO.addUser(user);
        } else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String role = request.getParameter("role");

            User user = new User();
            user.setId(id);
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(role);

            userDAO.updateUser(user);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            userDAO.deleteUser(id);
        }

        response.sendRedirect("UserManagementServlet");
    }
}