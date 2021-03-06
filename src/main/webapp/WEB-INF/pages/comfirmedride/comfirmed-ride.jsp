<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" >
    <title>EeZee Ride - Driver Confirmed</title>
</head>
<body class="girl">
<nav class="navbar">
    <a href="/" class="logo">
        <h1 class="logo">EeZee Ride</h1>
    </a>
    <ul class="nav-links">
        <li class="nav-item"><a href="/Logout">Log Out</a></li>
    </ul>
</nav>

<div class="nfcontainer">
    <div class="ride">
        <div><h1 class="title">Your driver is on the way!</h1></div>

        <div>
            <a href="ComfirmedRide"  style="margin-right: 10px">
                <button class="login-submit" type="submit">Refresh</button>
            </a>
            <a href="CancelRide">
                <button class="login-submit" type="submit">Cancel Ride</button>
            </a>
        </div>
    </div>

    <div>
        <h3>Please refresh the page to update your driver</h3>
        <table class="t-details">
            <tr>
                <th>Pick-up Location</th>
                <th>Drop-off Location</th>
                <th>Sub-Total</th>
                <th>GST</th>
                <th>Total</th>
            </tr>
            <tr>
                <td>${trip.startName}</td>
                <td>${trip.endName}</td>
                <td><fmt:formatNumber value = "${trip.paidAmount}" type = "currency" currencySymbol="RF "/></td>
                <td><fmt:formatNumber value = "${trip.paidAmount*0.06}" type = "currency" currencySymbol="RF "/></td>
                <td><fmt:formatNumber value = "${trip.paidAmount*1.06}" type = "currency" currencySymbol="RF "/></td>
            </tr>
        </table>
    </div>

    <div class="ride">
        <table class="t-ride">
            <tr>
                <th>Driver Name</th>
                <th>Vehicle Type</th>
                <th>License Plate No.</th>
            </tr>
            <tr>
                <td>${driver.firstName}</td>
                <td>${driver.primaryVehicle.manufacturer}</td>
                <td>${driver.primaryVehicle.licensePlateNo}</td>
            </tr>
        </table>

    </div>
</div>
</body>
</html>
