package servlet;

import dao.ItemDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/items/delete")
public class DeleteItemServlet extends HttpServlet {
    private final ItemDAO dao = new ItemDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            dao.delete(id);
            resp.sendRedirect("manageItems.jsp?success=Item%20deleted");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
