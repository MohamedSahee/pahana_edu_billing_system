package servlet;

import dao.ItemDAO;
import model.Item;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/items/update")
public class UpdateItemServlet extends HttpServlet {
    private final ItemDAO dao = new ItemDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            int stock = Integer.parseInt(req.getParameter("stock"));
            dao.update(new Item(id, name, price, stock));
            resp.sendRedirect("manageItems.jsp?success=Item%20updated");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
