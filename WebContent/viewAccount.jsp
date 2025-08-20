<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Account</title>
    <link rel="stylesheet" href="css/style.css" />
</head>
<body>
<div class="container">
    <h2>Customer Bills</h2>
    <ul>
        <%
            List<String> bills = (List<String>) request.getAttribute("bills");
            if (bills != null) {
                for (String b : bills) {
        %>
        <li><%= b %></li>
        <% }} %>
    </ul>
</div>
</body>
</html>