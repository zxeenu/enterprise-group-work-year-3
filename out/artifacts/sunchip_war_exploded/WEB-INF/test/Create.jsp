<%--
  Created by IntelliJ IDEA.
  User: zeenu
  Date: 09-Mar-21
  Time: 11:28 PM
  To change this template use File | Settings | File Templates.
  this jsp file is only for tesitng
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    Put some details, hit submit to find something by Alien ID
    <form action="getAlien">
        <input type="text" name="aid">
        <input type="submit">
    </form>

    Put some details, hit submit
    <form action="createAlien" method="post">
        <p>Alien ID</p>
        <input type="text" name="aid">
        <br>
        <p>Alien Name</p>
        <input type="text" name="name">
        <br>
        <p>Alien Tech</p>
        <input type="text" name="tech">
        <br>
        <input type="submit">
    </form>

</body>
</html>
