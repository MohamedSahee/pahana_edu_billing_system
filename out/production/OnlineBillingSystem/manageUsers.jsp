<!DOCTYPE html>
<html>
<head>
    <title>Manage Users - Online Billing System</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div class="container">
    <header>
        <h1>Online Billing System</h1>
        <nav>
            <a href="dashboard.jsp">Dashboard</a>
            <a href="addCustomer.jsp">Add Customer</a>
            <a href="manageItems.jsp">Manage Items</a>
            <a href="bill.jsp">Generate Bill</a>
            <a href="UserManagementServlet">Manage Users</a>
            <a href="ViewAccountServlet">My Account</a>
            <a href="HelpServlet">Help</a>
            <a href="LogoutServlet">Logout</a>
        </nav>
    </header>

    <main>
        <h2>Manage Users</h2>

        <!-- Add User Form -->
        <div class="add-user-form">
            <h3>Add New User</h3>
            <form action="UserManagementServlet" method="post">
                <input type="hidden" name="action" value="add">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="form-group">
                    <label for="role">Role:</label>
                    <select id="role" name="role" required>
                        <option value="admin">Admin</option>
                        <option value="staff">Staff</option>
                    </select>
                </div>
                <button type="submit">Add User</button>
            </form>
        </div>

        <!-- Users List -->
        <div class="users-list">
            <h3>Existing Users</h3>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Role</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <%
                    java.util.List<User> users = (java.util.List<User>) request.getAttribute("users");
                    if (users != null) {
                        for (User user : users) {
                %>
                <tr>
                    <td><%= user.getId() %></td>
                    <td><%= user.getUsername() %></td>
                    <td><%= user.getRole() %></td>
                    <td>
                        <form action="UserManagementServlet" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="<%= user.getId() %>">
                            <button type="submit" onclick="return confirm('Are you sure you want to delete this user?')">Delete</button>
                        </form>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>
        </div>

        <% if (request.getParameter("success") != null) { %>
        <p class="success">Operation completed successfully!</p>
        <% } %>
    </main>
</div>
</body>
</html>