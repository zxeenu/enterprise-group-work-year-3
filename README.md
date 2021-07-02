<div>
  <h6>University of West of England [Villa College]<br>
      BSc (HONS) Computer Science - Jan 2019<br>
      Enterprise Systems Development (UFCF85-30-3)<br>
      Group Assignment</h6>
</div>
<p align="center">
<img src="doc-images/EeZee Ride.png">
</p>
<p align="center"><i><b>The Taxi App You <strike>Need</strike> Deserve</b></i></p>

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#team">Team</a></li>
    <li><a href="#license">License</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
## About The Project
<p>Eezee Ride is a taxi app that can help customers and taxi drivers easily find each other. 
It takes into account the locations of both parties and driver availability to select the closest driver with maximum efficiency.</p>
<p>The app also calculated the fare automatically based on the distance travelled.</p>
<p>Admins will be able to view sales reports generated automatically from these transactions.</p>

### Built With
* Java 
* Dependency management: Maven 
* Database: JDBC with Microsoft SQL Server
* Spring Boot MVC
* Frontend: JavaServer Pages (JSP)


<!-- USAGE EXAMPLES -->
## Usage/Features
### For customers
#### Sign Up as a new user
<p>You can go to the sign up page by clicking the "Don't have an account?" on the home (log in) page, or by adding `SignUp` to the end of the root URL.</p>
<p>Once registered as a new user, you will be automatically redirected to the log in page.</p>

#### Log in
<p>To log in you will need your username and password.</p>
<p>Once logged in you will be taken to the Request A Ride page.</p>


#### Request a ride
<p>To request a ride, enter the location and destination, and the day and time of when you want the ride.</p>
<p>Enter the date and time easily by clicking the icons at the end of their fields.</p>
<p>After entering the information, click the Request button.</p>

#### Log out
<p>You can log out any time by clicking the "Log Out" in the header.</p>


### For drivers
#### Log in as a driver
#### Log out
<p>An Eezee Ride driver account will be created for you by an admin.</p>
<p>Log in with your username and password from the home (log in) page.</p>
<p>You will be redirected to the Assigned Jobs page.</p>

#### Assigned jobs
<p>From this page you will see the jobs that have been automatically assigned to you.</p>
<p>You have the option to accept or reject these jobs with a given reason.</p>

#### Log out
<p>You can log out any time by clicking the "Log Out" in the header.</p>


### For admins
#### Log in as an admin
<p>To log in as an admin go to `/LoginAdmin`.</p>
<p>You will be redirected to the admin dashboard.</p>

#### Admin Dashboard
<p>The dashboard allows drivers to view reports.</p>
<p>To filter the data by a driver</p>
<p>To filter the data within a data range select a start and end date and click the "Filter" button. Click the calendar icon to easily pick dates.</p>
<p>To view all drivers click "All Drivers" and to view all jobs irrespective of dates click "All Jobs".</p>

#### Drivers admin page
<p>Move to the drivers page via the link in the header.</p>
<p>Here, you can view a list of all drivers along with their information.</p>
<p>This is also where you can add new drivers who have joined the company.</p>

#### Customers admin page
<p>The customers page can also be accessed via the link in the header.</p>
<p>This page displays a list of all customers with their account ID, and first and last names. It also gives you the ability to delete the user.</p>

#### Log out
<p>You can log out any time by clicking the "Log Out" in the header.</p>



<!-- Team -->
## Team

The Sunchip Team

<table>
    <tr>
        <th>Team Member</th>
        <th>Main Responsibilities</th>
    </tr>
    <tr>
        <td><a href="https://github.com/zxeenu">Ziaan</a></td>
        <td>Backend development, Backend project management</td>
    </tr>
    <tr>
        <td><a href="https://github.com/arkangel-dev">Isaam</a></td>
        <td>Backend development, Develop architecture & diagrams</td>
    </tr>
    <tr>
        <td><a href="https://github.com/nah-y">Nahidha</a></td>
        <td>Backend development, Documentation</td>
    </tr>
    <tr>
        <td><a href="https://github.com/Jaaaxe">Yasmeen</a></td>
        <td>Frontend development, Frontend project management</td>
    </tr>
    <tr>
        <td><a href="https://github.com/notshaffan">Shaffan</a></td>
        <td>System UI/UX design, Testing</td>
    </tr>
    <tr>
        <td><a href="https://github.com/Hsain-r">Hussain</a></td>
        <td>Frontend development, Documentation</td>
    </tr>
</table>



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.

