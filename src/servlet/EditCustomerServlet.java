package servlet;

import dao.CustomerDAO;
import model.Customer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/customers/edit")
public class EditCustomerServlet extends HttpServlet {
    private final CustomerDAO dao = new CustomerDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String phone = req.getParameter("phone");
            String email = req.getParameter("email");
            String address = req.getParameter("address");
            dao.update(new Customer(id, name, phone, email, address));
            resp.sendRedirect("editCustomer.jsp?success=Customer%20updated");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Delete via GET ?delete=ID for simplicity
        String del = req.getParameter("delete");
        if (del != null) {
            try {
                dao.delete(Integer.parseInt(del));
                resp.sendRedirect("editCustomer.jsp?success=Customer%20deleted");
            } catch (SQLException e) {
                throw new ServletException(e);
            }
        } else {
            resp.sendRedirect("editCustomer.jsp");
        }
    }
}
