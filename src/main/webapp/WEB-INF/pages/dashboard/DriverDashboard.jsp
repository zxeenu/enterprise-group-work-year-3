<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="css/styles.css"/>
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
            <td>${trip.distance}</td>
            <td>
                <a href="DriverAcceptsJob?trip_id=${trip.ID}&driver_id=${driver.ID}"
                >
                    <button type="submit" id="accept" formmethod="post">Accept</button>
                </a
                >
                <a href="#"
                >
                    <button type="submit" id="reject" formmethod="post">Reject</button>
                </a
                >
                <%--TODO: currently button is off when trip has been completed, but it also needs to be disabled if the driver hasnt accepted the job, which cannot be known yet--%>
                <c:choose>
                    <c:when test="${trip.tripComplete == true}">
                        <a href="DriverCompletesJob?trip_id=${trip.ID}&driver_id=${driver.ID}"
                        >
                            <button type="submit" disabled formmethod="POST">Completed</button>
                        </a
                        >
                    </c:when>
                    <c:otherwise>
                        <a href="DriverCompletesJob?trip_id=${trip.ID}&driver_id=${driver.ID}"
                        >
                            <button type="submit" formmethod="POST">Completed</button>
                        </a
                        >
                    </c:otherwise>
                </c:choose>
            </td>
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
