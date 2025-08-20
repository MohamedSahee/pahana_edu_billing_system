<%@ page import="model.User" %>
<%
    if (session.getAttribute("user") == null) { response.sendRedirect("index.jsp"); return; }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Customer</title>
    <link rel="stylesheet" href="css/style.css" />
</head>
<body>
<div class="container">
    <h2>Add Customer</h2>
    <form method="post" action="customers/add">
        <input name="name" placeholder="Name" required/>
        <input name="phone" placeholder="Phone"/>
        <input name="email" placeholder="Email"/>
        <input name="address" placeholder="Address"/>
        <button type="submit">Add</button>
    </form>
    <p class="msg"><%= request.getParameter("error") != null ? request.getParameter("error") : "" %></p>
</div>
</body>
</html>
