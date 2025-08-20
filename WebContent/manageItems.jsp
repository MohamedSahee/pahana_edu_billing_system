<%@ page import="dao.ItemDAO,model.Item,java.util.List" %>
<%
    if (session.getAttribute("user") == null) { response.sendRedirect("index.jsp"); return; }
    ItemDAO dao = new ItemDAO();
    List<Item> list = dao.all();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Items</title>
    <link rel="stylesheet" href="css/style.css" />
</head>
<body>
<div class="container">
    <h2>Add Item</h2>
    <form method="post" action="items/add">
        <input name="name" placeholder="Name" required/>
        <input name="price" placeholder="Price" type="number" step="0.01" required/>
        <input name="stock" placeholder="Stock" type="number" required/>
        <button type="submit">Add</button>
    </form>

    <h2>Items</h2>
    <table>
        <tr><th>ID</th><th>Name</th><th>Price</th><th>Stock</th><th>Actions</th></tr>
        <%
            for (Item i : list) {
        %>
        <tr>
            <form method="post" action="items/update">
                <td><input type="hidden" name="id" value="<%= i.getId() %>"/><%= i.getId() %></td>
                <td><input name="name" value="<%= i.getName() %>"/></td>
                <td><input name="price" value="<%= i.getPrice() %>" type="number" step="0.01"/></td>
                <td><input name="stock" value="<%= i.getStock() %>" type="number"/></td>
                <td>
                    <button type="submit">Save</button>
                    <a class="danger" href="items/delete?id=<%= i.getId() %>">Delete</a>
                </td>
            </form>
        </tr>
        <% } %>
    </table>

    <p class="msg"><%= request.getParameter("success") == null ? "" : request.getParameter("success") %></p>
</div>
</body>
</html>
