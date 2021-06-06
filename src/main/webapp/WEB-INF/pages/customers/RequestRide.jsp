<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="css/styles.css" />
    <title>EeZee Ride - Request Ride</title>
  </head>
  <body class="sun">
    <nav class="navbar">
      <a href="/" class="logo">
        <h1 class="logo">EeZee Ride</h1>
      </a>
    </nav>

    <div class="nfcontainer">
      <h1 class="title">Request a ride</h1>

      <form action="/request" method="post" class="request-form">
        <div class="request-box">
          <input type="text" placeholder="Location" name="location" required />

          <input
            type="text"
            placeholder="Destination"
            name="destination"
            required
          />

          <input type="datetime-local" id="pickup" name="pickup" />
          <a href="TODO" target="_blank"
            ><button
              class="pickup-submit"
              type="submit"
              style="margin-top: 20px"
            >
              Request
            </button></a
          >
        </div>
      </form>
    </div>
  </body>
</html>
