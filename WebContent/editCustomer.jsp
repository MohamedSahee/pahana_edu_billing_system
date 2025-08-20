<%@ page import="dao.CustomerDAO,model.Customer,java.util.List" %>
<%
    if (session.getAttribute("user") == null) { response.sendRedirect("index.jsp"); return; }
    CustomerDAO dao = new CustomerDAO();
    List<Customer> list = dao.all();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Customers</title>
    <link rel="stylesheet" href="css/style.css" />
</head>
<body>
<div class="container">
    <h2>Customers</h2>
    <table>
        <tr><th>ID</th><th>Name</th><th>Phone</th><th>Email</th><th>Address</th><th>Actions</th></tr>
        <%
            for (Customer c : list) {
        %>
        <tr>
            <td><%= c.getId() %></td>
            <td>
                <form method="post" action="customers/edit" class="inline">
                    <input type="hidden" name="id" value="<%= c.getId() %>"/>
                    <input name="name" value="<%= c.getName() %>" />
                    <input name="phone" value="<%= c.getPhone() %>" />
                    <input name="email" value="<%= c.getEmail() %>" />
                    <input name="address" value="<%= c.getAddress() %>" />
                    <button type="submit">Save</button>
                </form>
            </td>
            <td colspan="2"></td>
            <td>
                <a class="danger" href="customers/edit?delete=<%= c.getId() %>">Delete</a>
            </td>
        </tr>
        <% } %>
    </table>
    <p class="msg"><%= request.getParameter("success") == null ? "" : request.getParameter("success") %></p>
</div>
</body>
</html>
