package servlet;

import dao.CustomerDAO;
import model.Customer;
import service.ValidationService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/customers/add")
public class AddCustomerServlet extends HttpServlet {
    private final CustomerDAO dao = new CustomerDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        if (!ValidationService.notEmpty(name)) {
            resp.sendRedirect("addCustomer.jsp?error=Name%20is%20required");
            return;
        }
        try {
            dao.add(new Customer(name, phone, email, address));
            resp.sendRedirect("editCustomer.jsp?success=Customer%20added");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
