<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
<%--    <link rel="stylesheet" href="css/styles.css" />--%>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" >
    <title>EeZee Ride - Customers</title>
</head>
<body class="dark">
<nav class="navbar admin">
    <a href="Home" class="logo">
        <h1 class="logo">EeZee Ride</h1>
    </a>
    <ul class="nav-links">
        <li class="nav-item"><a href="Dashboard">Dashboard</a></li>
        <li class="nav-item"><a href="Drivers">Drivers</a></li>
        <li class="nav-item"><a href="Customers">Customers</a></li>
        <li class="nav-item"><a href="Logout">Log Out</a></li>
    </ul>
</nav>

<div class="nfcontainer">
    <h1 class="title-dark">Customers</h1>

    <table class="dash">
        <thead>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Id</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${customerList}" var="customer">
                <tr>
                    <td>${customer.firstName}</td>
                    <td>${customer.lastName}</td>
                    <td>${customer.ID}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>


</div>
</body>