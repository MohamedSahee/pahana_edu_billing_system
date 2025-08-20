<%@ page import="dao.ItemDAO,dao.CustomerDAO,model.Item,model.Customer,java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%
    if (session.getAttribute("user") == null) { response.sendRedirect("index.jsp"); return; }
    ItemDAO itemDAO = new ItemDAO();
    CustomerDAO custDAO = new CustomerDAO();
    List<Item> items = null;
    try {
        items = itemDAO.all();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    List<Customer> customers = custDAO.all();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Create Bill</title>
    <link rel="stylesheet" href="css/style.css" />
</head>
<body>
<div class="container">
    <h2>Create Bill</h2>
    <form method="post" action="bill/create">
        <label>Customer</label>
        <select name="customerId" required>
            <option value="">-- choose --</option>
            <% for (Customer c : customers) { %>
            <option value="<%= c.getId() %>"><%= c.getName() %></option>
            <% } %>
        </select>
        <h3>Items</h3>
        <table>
            <tr><th>Item</th><th>Price</th><th>Qty</th></tr>
            <% for (Item i : items) { %>
            <tr>
                <td><%= i.getName() %></td>
                <td><%= i.getPrice() %></td>
                <td><label>
                    <input type="number" name="item_<%= i.getId() %>" min="0" value="0"/>
                </label></td>
            </tr>
            <% } %>
        </table>
        <button type="submit">Create Bill</button>
    </form>
    <p class="msg"><%= request.getParameter("success") == null ? "" : request.getParameter("success") %></p>
</div>
</body>
</html>
