<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - Online Billing System</title>
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
            <%
                String userRole = (String) session.getAttribute("userRole");
                if ("admin".equals(userRole)) {
            %>
            <a href="UserManagementServlet">Manage Users</a>
            <% } %>
            <a href="ViewAccountServlet">My Account</a>
            <a href="HelpServlet">Help</a>
            <a href="LogoutServlet">Logout</a>
        </nav>
    </header>

    <main>
        <h2>Welcome, <%= session.getAttribute("user") != null ? ((User)session.getAttribute("user")).getUsername() : "Guest" %>!</h2>
        <p>Your role: <strong><%= userRole %></strong></p>

        <div class="dashboard-stats">
            <div class="stat-card">
                <h3>Total Customers</h3>
                <p>150</p>
            </div>
            <div class="stat-card">
                <h3>Total Items</h3>
                <p>45</p>
            </div>
            <div class="stat-card">
                <h3>Today's Bills</h3>
                <p>12</p>
            </div>
            <% if ("admin".equals(userRole)) { %>
            <div class="stat-card">
                <h3>Total Users</h3>
                <p>5</p>
            </div>
            <% } %>
        </div>
    </main>
</div>
</body>
</html>