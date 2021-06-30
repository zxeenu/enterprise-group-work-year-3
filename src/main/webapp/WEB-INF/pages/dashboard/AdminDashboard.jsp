<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
<%--    <link rel="stylesheet" href="css/styles.css" />--%>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" >
    <title>EeZee Ride - Dashboard</title>
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
    <h1 class="title-dark">Dashboard</h1>

    <div class="filter">
        <div class="dropdown">
            <select name="drivers" id="drivers">
                <option selected value="All">All Drivers</option>
                <option value="sam">Sam</option>
                <option value="nahy">Nahy</option>
                <option value="bob">Bob</option>
                <option value="fff">Fff</option>
            </select>

            <select name="status" id="status">
                <option selected value="All">All Jobs</option>
                <option value="sam">Ongoing</option>
                <option value="nahy">Rejected</option>
                <option value="bob">Completed</option>
            </select>
            <!-- IF POSSIBLE: We need to set this to default to today and show all of today's history on initial load -->
        </div>
        <input
                type="date"
                value="TODO"
                id="history-start"
                name="history-start"
        />

        <input type="date" value="TODO" id="history-end" name="history-end" />
        <a href="/TODO"><button>Select</button></a>
    </div>

    <table>
        <tr>
            <th>Driver</th>
            <th>Ongoing Jobs</th>
            <th>Rejected Jobs</th>
            <th>Completed Jobs</th>

            <th>Daily Turnover</th>
        </tr>
        <tr>
            <td>All</td>
            <td>1</td>
            <td>2</td>
            <td>7</td>
            <td>1300.00 MVR</td>
        </tr>
    </table>
    <h1 class="title-dark">Job History</h1>
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