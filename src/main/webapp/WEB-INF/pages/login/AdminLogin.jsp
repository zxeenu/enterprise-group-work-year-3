<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="css/styles.css" />
  <title>EeZee Ride - Admin Login</title>
</head>
<body class="dark">
<nav class="navbar">
  <a href="Login" class="logo">
    <h1 class="logo">EeZee Ride</h1>
  </a>
</nav>

<div class="container center">
  <div class="login-box-container admin">
    <form action="LoginAdmin" method="post" class="admin-login">
      <div class="login-box">
        <h1 class="title">Admin</h1>
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
