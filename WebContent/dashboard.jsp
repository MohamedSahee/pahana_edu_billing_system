<%@ page import="model.User" %>
<%
    User u = (User) session.getAttribute("user");
    if (u == null) { response.sendRedirect("index.jsp"); return; }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="css/style.css" />
</head>
<body>
<div class="nav">
    <span>Welcome, <%= u.getUsername() %> (<%= u.getRole() %>)</span>
    <a href="manageItems.jsp">Items</a>
    <a href="addCustomer.jsp">Add Customer</a>
    <a href="editCustomer.jsp">Edit Customers</a>
    <a href="bill.jsp">Create Bill</a>
    <a href="help.jsp">Help</a>
    <a class="right" href="logout">Logout</a>
</div>
<div class="container">
    <h2>Dashboard</h2>
    <p>Use the navigation links to manage items, customers and bills.</p>
</div>
</body>
</html>
