<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" >
    <title>EeZee Ride - Driver Dashboard</title>
</head>
<body class="dark">
<nav class="navbar admin">
    <a href="Login" class="logo">
        <h1 class="logo">EeZee Ride</h1>
    </a>
    <ul class="nav-links">
        <li class="nav-item"><a href="Login" class="disabled">Hi, ${driver.firstName} ${driver.lastName}</a></li>
        <li class="nav-item"><a href="Logout">Log Out</a></li>
    </ul>
</nav>
<div class="nfcontainer">
    <h1 class="title-dark">Assigned Jobs</h1>
    <table class="dash">
        <thead>
        <tr>
            <th>Customer Name</th>
            <th>Location</th>
            <th>Coordinates</th>
            <th>Destination</th>
            <th>Coordinates</th>
            <th>Distance</th>
            <th>Status</th>
            <th>Rejection Reason</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <c:forEach items="${tripList}" var="trip">
        <tr>
            <td>${trip.customer.firstName} ${trip.customer.lastName}</td>
            <td>${trip.startName}</td>
            <td>(${trip.startLattitude}, ${trip.startLongtitude})</td>
            <td>${trip.endName}</td>
            <td>(${trip.endLattitude}, ${trip.endLongtitude})</td>
            <td><fmt:formatNumber value = "${trip.distance}" type = "currency" currencySymbol="KM "/></td>
            <td>
                <c:choose>
                    <%-- trip in state -2, rejected --%>
                    <c:when test="${trip.state == -2}">
                        <a href="DriverAcceptsJob?trip_id=${trip.ID}&driver_id=${driver.ID}"><button type="submit" disabled formmethod="POST">Accept</button></a>
                        <a href="DriverRejectsJob?trip_id=${trip.ID}&driver_id=${driver.ID}"><button type="submit" disabled formmethod="POST">Reject</button></a>
                        <a href="DriverCompletesJob?trip_id=${trip.ID}&driver_id=${driver.ID}"><button type="submit" disabled formmethod="POST">Completed</button></a>
                    </c:when>
                    <%-- trip in state 0, awaiting pickup --%>
                    <c:when test="${trip.state == 0}">
                        <a href="DriverAcceptsJob?trip_id=${trip.ID}&driver_id=${driver.ID}"><button type="submit" formmethod="POST">Accept</button></a>
                        <a href="DriverRejectsJob?trip_id=${trip.ID}&driver_id=${driver.ID}"><button type="submit" formmethod="POST">Reject</button></a>
                        <a href="DriverCompletesJob?trip_id=${trip.ID}&driver_id=${driver.ID}"><button type="submit" disabled formmethod="POST">Completed</button></a>
                    </c:when>
                    <%-- trip in state 1, in progress --%>
                    <c:when test="${trip.state == 1}">
                        <a href="DriverAcceptsJob?trip_id=${trip.ID}&driver_id=${driver.ID}"><button type="submit" disabled formmethod="POST">Accept</button></a>
                        <a href="DriverRejectsJob?trip_id=${trip.ID}&driver_id=${driver.ID}"><button type="submit" disabled formmethod="POST">Reject</button></a>
                        <a href="DriverCompletesJob?trip_id=${trip.ID}&driver_id=${driver.ID}"><button type="submit" formmethod="POST">Completed</button></a>
                    </c:when>
                    <%-- trip in state 2, complete --%>
                    <c:when test="${trip.state == 2 || trip.tripComplete == true}">
                        <a href="DriverAcceptsJob?trip_id=${trip.ID}&driver_id=${driver.ID}"><button type="submit" disabled formmethod="POST">Accept</button></a>
                        <a href="DriverRejectsJob?trip_id=${trip.ID}&driver_id=${driver.ID}"><button type="submit" disabled formmethod="POST">Reject</button></a>
                        <a href="DriverCompletesJob?trip_id=${trip.ID}&driver_id=${driver.ID}"><button type="submit" disabled formmethod="POST">Completed</button></a>
                    </c:when>
                </c:choose>
            </td>
            <td>${trip.getRejectionReasons}</td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script>
    $(document).ready(function () {
        $("#accept").click(function () {
            $("#completed").prop("disabled", false);
            $("#reject").prop("disabled", true);
        });
        $("#completed").click(function () {
            $("#accept").prop("disabled", true);
            $("#completed").addClass("button-clicked");
        });
        $("#reject").click(function () {
            $("#accept").prop("disabled", true);
            $("#completed").prop("disabled", true);
            $("#reject").addClass("button-clicked");
        });
    });
</script>
</html>
