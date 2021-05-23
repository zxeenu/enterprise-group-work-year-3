<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="css/style.css" />
    <title>EeZee Ride</title>
  </head>
  <body>
    <div class="container">
      <h1 class="logo">EeZee Ride</h1>
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

            <%--     forgot password not mapped to anything yet       --%>
            <span class="forgot"> <a href="ForgotPassword">Forgot password?</a></span>
          </div>
        </form>
      </div>
    </div>
  </body>
</html>
