<%--
  Created by IntelliJ IDEA.
  User: zeenu
  Date: 09-Mar-21
  Time: 10:37 PM
  To change this template use File | Settings | File Templates.

  ${errorMessage}

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="css/styles.css" />
    <title>404</title>
</head>
<body class="girl">
<nav class="navbar">
    <a href="Home" class="logo">
        <h1 class="logo">EeZee Ride</h1>
        <ul class="nav-links">
            <li class="nav-item"><a href="./index.html">Log Out</a></li>
        </ul>
    </a>
</nav>

<div class="nfcontainer">
    <h1 class="title">Woops</h1>
    <h1 class="sub-title">It looks like you took a wrong turn</h1>
    <br>
    <br>
    <h1 class="sub-title">${errorMessage}</h1>
    <br>
    <div style="width: 200px; margin-top: 10px">
        <a href="Home"><button>GO BACK HOME</button></a>
    </div>
</div>
</body>