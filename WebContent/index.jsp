<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Online Billing - Login</title>
    <link rel="stylesheet" href="css/style.css" />
</head>
<body>
<div class="container">
    <h2>Login</h2>
    <form method="post" action="login">
        <input type="text" name="username" placeholder="Username" required/>
        <input type="password" name="password" placeholder="Password" required/>
        <button type="submit">Login</button>
    </form>
    <p class="error"><%= request.getAttribute("error") == null ? "" : request.getAttribute("error") %></p>
</div>
</body>
</html>
