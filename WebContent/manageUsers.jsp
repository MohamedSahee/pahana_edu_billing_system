<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, model.User" %>
<%
    List<User> users = (List<User>) request.getAttribute("users");
%>
<html>
<head>
    <title>Manage Users</title>
    <style>
        table { border-collapse: collapse; width: 80%; margin: auto; }
        th, td { padding: 8px; border: 1px solid #ccc; }
        th { background: #f4f4f4; }
        form { display: inline; }
    </style>
</head>
<body>
<h2 style="text-align:center;">Manage Users</h2>

<!-- Add User Form -->
<form method="post" action="ManageUsersServlet" style="text-align:center;">
    <input type="hidden" name="action" value="add">
    <input type="text" name="username" placeholder="Username" required>
    <input type="password" name="password" placeholder="Password" required>
    <select name="role">
        <option value="Admin">Admin</option>
        <option value="Cashier">Cashier</option>
    </select>
    <button type="submit">Add User</button>
</form>

<!-- Users Table -->
<table>
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Role</th>
        <th>Actions</th>
    </tr>
    <%
        if (users != null) {
            for (User u : users) {
    %>
    <tr>
        <td><%= u.getId() %></td>
        <td><%= u.getUsername() %></td>
        <td><%= u.getRole() %></td>
        <td>
            <!-- Edit Form -->
            <form method="post" action="ManageUsersServlet">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="<%= u.getId() %>">
                <input type="text" name="username" value="<%= u.getUsername() %>">
                <select name="role">
                    <option value="Admin" <%= "Admin".equals(u.getRole()) ? "selected" : "" %>>Admin</option>
                    <option value="Cashier" <%= "Cashier".equals(u.getRole()) ? "selected" : "" %>>Cashier</option>
                </select>
                <button type="submit">Update</button>
            </form>

            <!-- Delete Form -->
            <form method="post" action="ManageUsersServlet">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="id" value="<%= u.getId() %>">
                <button type="submit" onclick="return confirm('Delete user?')">Delete</button>
            </form>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>

</body>
</html>
