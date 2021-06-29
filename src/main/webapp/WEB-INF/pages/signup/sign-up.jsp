<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="css/styles.css" />
    <title>Eezee Ride - Sign Up</title>
</head>
<body class="light">
<nav class="navbar">
    <a href="/" class="logo">
        <h1 class="logo">EeZee Ride</h1>
    </a>
</nav>
<div class="container center">
    <div class="fifty-col">
        <h1 class="hero-title">Sign Up</h1>

        <div class="login-box-container">
            <form:form action="SignUp" method="post" modelAttribute="signUpForm">
                <div class="login-box">
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

                    <button class="login-submit" type="submit">Sign Up</button>
                    <div class="forgot">
                        <a href="./index.html">Already have an account?</a>
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
