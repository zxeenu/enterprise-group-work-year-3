<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="css/styles.css" />
    <title>EeZee Ride - Dashboard</title>
</head>
<body class="dark">
<nav class="navbar admin">
    <a href="/" class="logo">
        <h1 class="logo">EeZee Ride</h1>
    </a>
    <ul class="nav-links">
        <li class="nav-item"><a href="Login">Dashboard</a></li>
        <li class="nav-item"><a href="./drivers.html">Drivers</a></li>
        <li class="nav-item"><a href="./customers.html">Customers</a></li>
        <li class="nav-item"><a href="Logout">Log Out</a></li>
    </ul>
</nav>

<div class="nfcontainer">
    <h1 class="title-dark">Dashboard</h1>
    <div class="secondary-title">
        <p>Active Drivers:</p>
        <p>Bookings Completed:</p>
        <p>Today's Turnover: MVR</p>
    </div>

    <table class="dash">
        <tr>
            <th>Driver</th>
            <th>Customer</th>
            <th>Location</th>
            <th>Destination</th>
            <th>Distance</th>
            <th>Status</th>
        </tr>
        <tr>
            <td>Sam</td>
            <td>Banaha</td>
            <td>Home</td>
            <td>College</td>
            <td>3 Kilometers</td>
            <td>In progress</td>
        </tr>
    </table>
</div>
</body>
</html>
