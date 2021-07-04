<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" >
    <title>EeZee Ride - Request Ride</title>
</head>
<body class="sun">
<nav class="navbar">
    <a href="Home" class="logo">
        <h1 class="logo">EeZee Ride</h1>
        <ul class="nav-links">
            <li class="nav-item"><a href="Logout">Log Out</a></li>
        </ul>
    </a>
</nav>

<div class="nfcontainer">
    <h1 class="title">Request a ride</h1>

    <form:form action="RequestRide" method="post" modelAttribute="requestForm">
        <div class="request-box">
            <form:input
                    path = "location"
                    placeholder="Location"
                    name="location"
                    required = "required"
            />

            <form:input
                    path="destination"
                    placeholder="Destination"
                    name="destination"
                    required = "required"
            />

            <form:input path="date" type="date" id="pickup" name="pickup" required = "required"/>
            <form:input path="time" type="time" id="pickup" name="pickup" required = "required"/>
            <button
                    class="pickup-submit"
                    type="submit"
                    style="margin-top: 20px"
            >
                Request
            </button>
        </div>
    </form:form>
</div>
</body>
</html>