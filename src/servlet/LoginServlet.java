package servlet;

import dao.UserDAO;
import model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            User u = userDAO.findByUsername(username);
            if (u != null && u.getPassword().equals(password)) { // NOTE: demo only
                HttpSession s = req.getSession(true);
                s.setAttribute("user", u);
                resp.sendRedirect("dashboard.jsp");
            } else {
                req.setAttribute("error", "Invalid credentials");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
