<%--
  Created by IntelliJ IDEA.
  User: zeenu
  Date: 19-Mar-21
  Time: 4:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User List</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>

<h1>User Account List</h1>
<table style="width:100%">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Password</th>
        <th>Type</th>
    </tr>
    <c:forEach items="${accounts}" var="p">
        <tr>
            <td>${p.id}</td>
            <td>${p.username}</td>
            <td>${p.password}</td>
            <td>${p.type}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
