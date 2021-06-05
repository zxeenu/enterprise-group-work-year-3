<!DOCTYPE html>
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
      <ul class="nav-links">
        <li class="nav-item"><a href="#">Login</a></li>
        <li class="nav-item"><a href="#">Sign Up</a></li>
      </ul>
    </nav>

    <div class="container center">
      <div class="fifty-col">
        <h1 class="hero-title">Sign Up</h1>

        <div class="login-box-container">
          <form action="/signup" method="post">
            <div class="login-box">
              <div style="display: flex">
                <input
                  type="radio"
                  name="user-type"
                  id="customer"
                  value="customer"
                  checked
                />
                <label for="customer">Customer</label>
                <input
                  type="radio"
                  name="user-type"
                  id="driver"
                  value="driver"
                />
                <label for="driver">Driver</label>
              </div>
              <input type="text" placeholder="Full Name" name="name" required />

              <input type="email" placeholder="Email" name="email" required />

              <input
                type="tel"
                placeholder="Mobile Number"
                name="mobile"
                required
              />

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

              <input
                type="password"
                placeholder="Confirm Password"
                name="confirm-password"
                required
              />

              <button class="login-submit" type="submit">Sign Up</button>
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