package servlet;

import service.BillingService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/bill/create")
public class BillServlet extends HttpServlet {
    private final BillingService billingService = new BillingService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int customerId = Integer.parseInt(req.getParameter("customerId"));
            // Expect params like item_1=2 (itemId_1 -> qty 2)
            Map<Integer,Integer> items = new HashMap<>();
            req.getParameterMap().forEach((k, v) -> {
                if (k.startsWith("item_")) {
                    int itemId = Integer.parseInt(k.substring(5));
                    int qty = Integer.parseInt(v[0]);
                    if (qty > 0) items.put(itemId, qty);
                }
            });
            int billId = billingService.createBill(customerId, items);
            resp.sendRedirect("bill.jsp?success=Bill%20created%20ID%3D" + billId);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
