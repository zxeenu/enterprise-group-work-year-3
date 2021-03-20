<%--
  Created by IntelliJ IDEA.
  User: zeenu
  Date: 09-Mar-21
  Time: 11:49 PM
  To change this template use File | Settings | File Templates.
  this jsp file is only for testing
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    ${alien.aid}
    ${alien.name}
    ${alien.tech}


    <br>
    <form action="alienView" method="post">
        <input type="submit" value="Back">
    </form>
</body>
</html>
