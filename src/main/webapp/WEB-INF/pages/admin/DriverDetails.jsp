<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
<%--    <link rel="stylesheet" href="css/styles.css" />--%>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" >
    <title>EeZee Ride - Drivers</title>
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
        <h1 class="title-dark">Drivers</h1>

        <a href="AddDriver" class="driver"><button>Add New</button></a>

        <table class="dash">
            <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Vehicle Type</th>
                <th>Plate Number</th>
                <th>Colour</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${driverList}" var="driver">
                <tr>
                    <td>${driver.firstName}</td>
                    <td>${driver.lastName}</td>
                    <td>${driver.primaryVehicle.manufacturer}</td>
                    <td>${driver.primaryVehicle.licensePlateNo}</td>
                    <td>${driver.primaryVehicle.color}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>