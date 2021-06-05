<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="css/styles.css" />
    <title>Eezee Ride</title>
  </head>
  <body class="light">
    <nav class="navbar">
      <a href="/" class="logo">
        <h1 class="logo">EeZee Ride</h1>
      </a>
      <ul class="nav-links">
        <li class="nav-item"><a href="Login">Login</a></li>
        <li class="nav-item"><a href="/SignUp">Sign Up</a></li>
      </ul>
    </nav>

    <div class="container center">
      <div class="fifty-col">
        <h1 class="hero-title">EeZee Ride</h1>

        <div class="login-box-container">
          <form action="Login" method="POST">
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

              <div class="forgot"><a href="ForgotPassword">Forgot password?</a></div>
            </div>
          </form>
        </div>
      </div>

      <div class="fifty-col">
        <img src="assets/images/hero.svg" />
      </div>
    </div>
  </body>
</html>
