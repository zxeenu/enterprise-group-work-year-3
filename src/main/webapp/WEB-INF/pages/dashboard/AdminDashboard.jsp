<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
<%--    <link rel="stylesheet" href="css/styles.css" />--%>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" >
    <title>EeZee Ride - Dashboard</title>
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
    <h1 class="title-dark">Dashboard</h1>

    <div class="filter">

        <form action="Filter" method="Post" class="admin-dash">


                <select name="driver" id="drivers">
                    <option value="all_drivers">All Drivers</option>
                    <c:forEach items="${driverList}" var="driver">
                        <option value="${driver.ID}">${driver.firstName} ${driver.lastName}</option>
                    </c:forEach>
                </select>

                <select name="status" id="status">
                    <option value="all_status">All Jobs</option>
                    <option value="ongoing">In Progress</option>
                    <option value="rejected">Rejected</option>
                    <option value="completed">Completed</option>
                </select>


                <input
                        type="date"
                        id="history-start"
                        name="history-start"
                />

                <input type="date" id="history-end" name="history-end" />

                <button formaction="Filter" formmethod="post">Filter</button>
                <a href="#"><button>Clear</button></a>

        </form>

    </div>

    <table>
        <tr>
            <th>Driver</th>
            <th>Ongoing Jobs</th>
            <th>Rejected Jobs</th>
            <th>Completed Jobs</th>
            <th>Daily Turnover</th>
        </tr>
        <tr>
            <td>${driverName}</td>
            <td>${inProgessJobs}</td>
            <td>${rejectedJobs}</td>
            <td>${completedJobs}</td>
            <td><fmt:formatNumber value = "${dailyTurnOver}" type = "currency" currencySymbol="MVR "/></td>
        </tr>
    </table>
    <h1 class="title-dark">Job History</h1>
    <table class="dash">
        <tr>
            <th>Driver</th>
            <th>Customer</th>
            <th>Location</th>
            <th>Destination</th>
            <th>Distance</th>
            <th>Status</th>
            <th>Rejection Reason</th>
        </tr>
        <c:forEach items="${tripList}" var="trip">
            <tr>
                <td>${trip.driver.firstName} ${trip.driver.lastName}</td>
                <td>${trip.customer.firstName} ${trip.customer.lastName}</td>
                <td>${trip.startName}</td>
                <td>${trip.endName}</td>
                <td><fmt:formatNumber value = "${trip.distance}" type = "currency" currencySymbol="KM "/></td>
                <td>
                    <c:choose>
                        <c:when test="${trip.state == -2}">
                            Rejected
                        </c:when>
                        <c:when test="${trip.state == 0}">
                            Awaiting Pickup
                        </c:when>
                        <c:when test="${trip.state == 1}">
                            In Progress
                        </c:when>
                        <c:when test="${trip.state == 2}">
                            Complete
                        </c:when>
                    </c:choose>
                </td>
                <td>${trip.getRejectionReasons}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>