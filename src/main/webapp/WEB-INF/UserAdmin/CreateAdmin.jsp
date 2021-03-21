<%--
  Created by IntelliJ IDEA.
  User: zeenu
  Date: 20-Mar-21
  Time: 9:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create New User</title>
</head>
<body>
    <form action="CreateAdminNewUser" method="post">
        <p>Username</p>
        <input type="text" name="username">
        <br>
        <p>User password</p>
        <input type="password" name="password">
        <br>
        <p>User email</p>
        <input type="email" name="email">
        <br>
        <br>
        <input type="submit" value="Register">
    </form>

    <form action="UserMain" method="post">
        <input type="submit" value="Go Back">
    </form>

</body>
</html>
