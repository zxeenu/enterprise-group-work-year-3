<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<<<<<<< HEAD
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
=======
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
      <ul class="nav-links">
        <li class="nav-item"><a href="Login">Login</a></li>
        <li class="nav-item"><a href="SignUp">Sign Up</a></li>
      </ul>
    </nav>
>>>>>>> 2193c7e0f9fa5109e1b402e1b4ade8873e00bb44

    <div class="login-box-container">
      <form:form action="/signup" method="post" modelAttribute="signUpForm">
        <div class="login-box">
          <div style="display: flex">
            <form:radiobutton
                    path="userType"
                    name="user-type"
                    id="customer"
                    value="customer"
                    checked = "checked"
            />
            <label for="customer">Customer</label>
            <form:radiobutton
                    path="userType"
                    name="user-type"
                    id="driver"
                    value="driver"
            />
            <label for="driver">Driver</label>
          </div>

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
                  required = "required"          />

          <br />

          <form:password
                  path="password"
                  placeholder="Password"
                  name="password"
                  required = "required"
          />

          <form:password
                  path=" "
                  placeholder="Confirm Password"
                  name="confirm-password"
                  required = "required"
          />

          <br />

          <button class="login-submit" type="submit">Sign Up</button>
          <div class="forgot">
            <a href="./index.html">Already have an account?</a>
          </div>
        </div>
      </form:form>
    </div>
  </div>

  <div class="fifty-col">
    <img src="assets/images/hero.svg" />
  </div>
</div>
</body>
</html>
