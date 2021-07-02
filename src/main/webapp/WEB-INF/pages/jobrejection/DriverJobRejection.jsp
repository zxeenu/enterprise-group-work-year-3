<%--
  Created by IntelliJ IDEA.
  User: zeenu
  Date: 02-Jul-21
  Time: 4:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" >
    <title>EeZee Ride - Reject Ride</title>
</head>
<body class="dark">
<nav class="navbar admin">
    <a href="/" class="logo">
        <h1 class="logo">EeZee Ride</h1>
    </a>
    <ul class="nav-links">
        <li class="nav-item"><a href="Logout">Log Out</a></li>
    </ul>
</nav>
<div class="nfcontainer">
    <h1 class="title-dark">Job Rejected</h1>
    <h1 class="sub-title-light">Please specify your reason</h1>

    <form action="DriverRejectsJob" id="reason" class="reject-humanity" method="post">
        <div>
          <textarea
                  name="rejection_reason"
                  form="reason"
                  cols="80"
                  rows="1"
                  placeholder="Enter your reason here"
                  style="resize: vertical; margin-top: 20px"
                  required
          ></textarea>
<%--            <a href="TODO" target="_blank"--%>
<%--            ><button type="submit" style="margin-top: 20px; width: 100px">--%>
<%--                Save--%>
<%--            </button></a--%>
<%--            >--%>
            <button type="submit" style="margin-top: 20px; width: 100px">
                Save
            </button><
        </div>
    </form>
</div>
</body>