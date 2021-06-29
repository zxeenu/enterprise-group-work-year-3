<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="css/styles.css" />
    <title>EeZee Ride - Drivers</title>
</head>
<body class="dark">
<nav class="navbar admin">
    <a href="Home" class="logo">
        <h1 class="logo">EeZee Ride</h1>
    </a>
    <ul class="nav-links">
        <li class="nav-item"><a href="AdminDashboard">Dashboard</a></li>
        <li class="nav-item"><a href="AdminDashboardDrivers">Drivers</a></li>
        <li class="nav-item"><a href="AdminDashboardCustomers">Customers</a></li>
        <li class="nav-item"><a href="Logout">Log Out</a></li>
    </ul>
</nav>

<div class="nfcontainer">
    <h1 class="title-dark">Drivers</h1>

    <a href="CreateDriver" class="driver"><button>Add New</button></a>

    <table class="dash">
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Vehicle Type</th>
            <th>Plate Number</th>
            <th>Colour</th>
        </tr>
        <tr>
            <td>Sam</td>
            <td>Ramirez</td>
            <td>Tesla</td>
            <td>P1234</td>
            <td>That's Racist</td>
        </tr>
    </table>
</div>
</body>