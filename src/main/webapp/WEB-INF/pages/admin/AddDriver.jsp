<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="css/styles.css" />
    <title>Eezee Ride - Sign Up Driver</title>
</head>
<body class="light">
<nav class="navbar">
    <a href="Home" class="logo">
        <h1 class="logo">EeZee Ride</h1>
    </a>
</nav>
<div class="container center">
    <div class="fifty-col">
        <h1 class="hero-title">Add new driver</h1>

        <div class="login-box-container">
            <form action="/signupdriver" method="post">
                <div class="login-box-driver">
                    <br />

                    <input
                            type="text"
                            placeholder="First Name"
                            name="fname"
                            required
                    />
                    <input
                            type="text"
                            placeholder="Last Name"
                            name="lname"
                            required
                    />

                    <input
                            type="text"
                            placeholder="Vehicle Type"
                            name="vehicle"
                            id="vehicle"
                            required
                    />

                    <input
                            type="text"
                            placeholder="License Plate Number"
                            name="plate"
                            id="plate"
                            required
                    />

                    <input
                            type="text"
                            placeholder="Colour"
                            name="colour"
                            id="colour"
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

                    <div class="save">
                        <a href="./drivers.html"><button type="submit">Save</button></a>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="fifty-col" style="height: 90vh">
        <img src="assets/images/hero.svg" />
    </div>
</div>
</body>