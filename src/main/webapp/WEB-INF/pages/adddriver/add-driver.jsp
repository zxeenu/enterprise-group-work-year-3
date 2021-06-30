<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="css/styles.css" />
    <title>Eezee Ride - Sign Up Driver</title>
</head>
<body class="light">
<nav class="navbar">
    <a href="/" class="logo">
        <h1 class="logo">EeZee Ride</h1>
    </a>
</nav>
<div class="container center">
    <div class="fifty-col">
        <h1 class="hero-title">Add new driver</h1>

        <div class="login-box-container">
            <form:form action="AddDriver" method="post" modelAttribute="driverForm">
                <div class="login-box-driver">
                    <br />

                    <form:input
                            path="firstName"
                            placeholder="First Name"
                            name="fname"
                            required = "required"
                    />

                    <form:input
                            path="lastName"
                            placeholder="Last Name"
                            name="lname"
                            required = "required"
                    />

                    <form:input
                            path="manufacturer"
                            placeholder="Vehicle Type"
                            name="manufacturer"
                            required = "required"
                    />

                    <form:input
                            path="licensePlateNo"
                            placeholder="License Plate Number"
                            name="plate"
                            required = "required"
                    />

                    <form:input
                            path="colour"
                            placeholder="Colour"
                            name="colour"
                            required = "required"
                    />

                    <form:input
                            path="username"
                            placeholder="Username"
                            name="username"
                            required = "required"
                    />

                    <form:password
                            path="password"
                            placeholder="Password"
                            name="password"
                            required = "required"
                    />

                    <form:password
                            path="confirmPassword"
                            placeholder="Confirm Password"
                            name="confirm-password"
                            required = "required"
                    />

                    <div class="save">
<%--                        <a href="./drivers.html"><button type="submit">Save</button></a>--%>
                        <button type="submit">Save</button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>

    <div class="fifty-col" style="height: 90vh">
        <img src="assets/images/hero.svg" />
    </div>
</div>
</body>
</html>

