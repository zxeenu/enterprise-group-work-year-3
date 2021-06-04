<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="css/styles.css"/>
    <title>EeZee Ride - Admin Login</title>
</head>
<body class="dark">
<nav class="navbar">
    <a href="/" class="logo">
        <h1 class="logo">EeZee Ride</h1>
    </a>
    <ul class="nav-links">
        <li class="nav-item"><a href="#">Contact Us</a></li>
        <li class="nav-item"><a href="#">Login</a></li>
        <li class="nav-item"><a href="#">Sign Up</a></li>
    </ul>
</nav>

<div class="container">
    <div class="login-box-container">
        <form action="LoginAdmin" method="POST">
            <div class="login-box">
                <input
                        type="text"
                        placeholder="Username"
                        name="username"
                        required
                />

                <input
                        type="password"
                        placeholder="Password"
                        name="password"
                        required
                />

                <button class="login-submit" type="submit">Login</button>

                <div class="forgot"><a href="ForgotPasswordAdmin">Forgot password?</a></div>
            </div>
        </form>
    </div>
</div>
</body>
</html>