package servlet;

import dao.ItemDAO;
import model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/items/add")
public class AddItemServlet extends HttpServlet {
    private final ItemDAO dao = new ItemDAO();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            int stock = Integer.parseInt(req.getParameter("stock"));
            dao.add(new Item(name, price, stock));
            resp.sendRedirect("manageItems.jsp?success=Item%20added");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
