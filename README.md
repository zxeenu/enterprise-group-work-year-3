<div>
  <p align="center"><img src="doc-images/EeZee Ride.png"></p>
  <p align="center"><i><b>The Taxi App You <strike>Need</strike> Deserve</b></i></p>
</div>

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about">About The Project</a>
      <ul>
        <li><a href="#built">Built With</a></li>
      </ul>
    </li>
    <li><a href="#instructions">Building The Application</a></li>
    <li><a href="#usage">Using The Application</a></li>
    <li><a href="#team">Team</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
<hr>
<h1 id="about">About The Project</h1>
<p>Eezee Ride is a taxi app that can help customers and taxi drivers easily find each other.</p>
<p>Customer can book a ride and a driver is automatically assigned. The app calculates the fare based on the distance travelled.</p>
<p>Admins will be able to view sales reports with the ability to filter drivers, dates and trip status to view statistics.</p>

<h3 id="built">Built With</h3>
<ul>
  <li>Java</li>
  <li>Dependency management: Maven</li>
  <li>main.Database: JDBC with Microsoft SQL Server</li>
  <li>Spring Boot MVC</li>
  <li>Frontend: JavaServer Pages (JSP)</li>
</ul>

<!-- BUILD INSTRUCTIONS -->
<hr>
<h1 id="instructions">Building The Application</h1>
<h3>Build your database</h3>
To use the application, you need to create a new MMSQL Database. You can use an MMSQL database tool, such as <a href="https://docs.microsoft.com/en-us/sql/ssms/download-sql-server-management-studio-ssms?view=sql-server-ver15" target="_blank">SSMS</a> if you have Windows or <a href="https://docs.microsoft.com/en-us/sql/azure-data-studio/download-azure-data-studio?view=sql-server-ver15" target="_blank">Azure Data Studio</a> if you have a Mac to create and manage your MMSQL database. If you're using Azure Data Studio, you would also need to setup Docker by following this <a href="https://www.freecodecamp.org/news/cjn-how-to-connect-your-microsoft-sql-server-docker-container-with-azure-data-studio/" target="_blank">tutorial</a>.
<ol>
  <li>Setup your MMSQL server</li>
  <li>Create a new database on your server. (SQL command to create database: CREATE DATABASE databasename;)</li>
</ol>
We will then connect this empty database to the application to create the required tables (you will find the instructions on how to make the connection string on line 16 of src > Spring > SunchipApplication).

If you are unable to create and connect to a database please go to src > Spring > SunchipApplication, comment out line 22 and uncomment line 25 to use our development database. If you are unable to form a connection please let us know so that we can confirm the details have not changed. 

<hr>
<h3>Clone the project</h3>
You must clone the main branch and the webservice branch to make the application work. The main branch is the web application and the webservice is needed to process the ride request.

Clone the main branch: git clone https://github.com/zxeenu/enterprise-group-work-year-3.git
Clone the webservice branch: git clone -b web-service https://github.com/zxeenu/enterprise-group-work-year-3.git

Alternatively, you can also run two instances of the application and switch the branch from main to web-services on one. 
<hr>
<h3>IntelliJ IDEA Setup</h3>
Before you run your application, you need to add the libraries and make sure the correct source file is selected. You need to do this for both the main and web-service instances of the project running on intelliJ IDEA.

To add the libraries:
<ol>
  <li>Go to File > Project Structure > Libraries</li>
  <li>Click the + icon and select Java</li>
  <li>Open lib and select all the files</li>
  <li>Click open and then ok when reconfirming if you want to add them to the project</li>
</ol>
To ensure correct source is chosen:
<ol>
  <li>Go to File > Project Structure > Modules</li>
  <li>Under the sources tab make sure your Source Folder says src</li>
  <li>If it doesn't, delete the path that's selected</li>
  <li>Expand the project folder and click on src and press Mark as sources button</li>
  <li>Click apply and then okay</li>
</ol>

<hr>
<h3>Run the application</h3>
Navigate to src > Spring > SunchipApplication and click run at the top right. You must do this on both branches by opening an instance of the main project and the web-service project, going through the intelliJ IDEA setup steps on both and running the SunchipApplication on both. Open a browser (the app is best viewed on Chrome) and go to http://localhost:8080/ and http://localhost:8081/ to check if both are running. If the main app is running, then on 8080 you should see the login page and if the web-service is running then on 8081 you should see a blank page with some text (since it's waiting on a request). 

<h3>Dummy Users</h3>
In order to quickly make users for the system as well as the admin login, you can head to the following <a href="http://localhost:8080/MakeStuffUp" target="_blank">link</a> after you're sure you are connected to a database. It will automaticallu generate the following users for you:

<table>
  <tr>
    <th>Type</th>
    <th>Username</th>
    <th>Password</th>
  </tr>
  <tr>
    <td>Admin</td>
    <td>Admin1</td>
    <td>Admin1</td>
  </tr>
  <tr>
    <td>Customer</td>
    <td>User1</td>
    <td>User1</td>
  </tr>
    <tr>
    <td>Customer</td>
    <td>User2</td>
    <td>User2</td>
  </tr>
    <tr>
    <td>Customer</td>
    <td>User2</td>
    <td>User2</td>
  </tr>
  </table>

Due to the nature of drivers, you will need to create them through the GUI. Please head to <a href="http://localhost:8080/LoginAdmin" target="_blank">Admin Login</a> and log in using the username Admin1 and password Admin1 and navigate to drivers <a href="http://localhost:8080/Admin/Drivers" target="_blank">Driver's page</a>. You may then click "Add Driver" and add one or more drivers to make the system work. Please note that a driver is required before you can use the system to book a ride. 
<!-- USAGE EXAMPLES -->
<hr>
<h1 id="usage">Using The Application</h1>
<h3>Customers</h3>
<h4>Sign Up as a new user</h4>
<p>You can sign up by clicking the "Sign Up" button on the navigation or the "Don't have an account?" link below the login form.</p>
<p>Once registered as a new user, you will be automatically redirected to the log in page where you can use your new account.</p>

<h4>Log in</h4>
<p>To log in you will need your username and password.</p>
<p>Once logged in you will be taken to the Request A Ride page.</p>

<h4>Request a ride</h4>
<p>To request a ride, enter the location and destination, and the day and time of when you want the ride.</p>
<p>Enter the date and time easily by clicking the icons at the end of their fields.</p>
<p>After entering the information, click the Request button.</p>

<h4>Log out</h4>
<p>You can log out any time by clicking the "Log Out" in the header.</p>
<hr>
<h3>Drivers</h3>
<h4>Log in as a driver</h4>
<p>An Eezee Ride driver account will be created for you by an admin.</p>
<p>Log in with your username and password from the home (log in) page.</p>
<p>You will be redirected to the Assigned Jobs page.</p>

<h4>Assigned jobs</h4>
<p>From this page you will see the jobs that have been automatically assigned to you.</p>
<p>You have the option to accept or reject these jobs with a given reason.</p>
<p>Once a job is accepted and paid for please mark it as completed.<p>

<h4>Log out</h4>
<p>You can log out any time by clicking the "Log Out" in the header.</p>
<hr>
<h3>Admins</h3>
<h4>Log in as an admin</h4>
<p>To log in as an admin go to `/LoginAdmin`.</p>
<p>You will be redirected to the admin dashboard.</p>

<h4>Admin Dashboard</h4>
<p>The dashboard allows admins to view reports. You can use the following filters to generate the specific data you need:</p>
<p>Driver: Select all or a specific driver</p>
<p>Start / End Date: To filter the data within a range select a start and end date.</p>
<p>Status: Select all jobs or a status from in progress, rejected and completed</p>

<h4>Drivers list</h4>
<p>Navigate to the drivers page via the link in the header.</p>
<p>Here, you can view a list of all drivers along with their information.</p>
<p>You can add new drivers by clicking "Add New" or delete a driver from the current drivers.</p>

<h4>Customers list</h4>
<p>Navigate to the customers page via the link in the header.</p>
<p>This page displays a list of all customers with their account ID, and first and last names. It also gives you the ability to delete a user.</p>

<h4>Log out</h4>
<p>You can log out any time by clicking the "Log Out" in the header.</p>

<!-- Team -->
<hr>
<h1 id="team">Team</h1>
The Sunchip Team

<table>
    <tr>
        <th>Team Member</th>
        <th>Main Responsibilities</th>
    </tr>
    <tr>
        <td><a href="https://github.com/zxeenu">Ziaan</a></td>
        <td>main.Backend development, main.Backend project management</td>
    </tr>
    <tr>
        <td><a href="https://github.com/arkangel-dev">Isaam</a></td>
        <td>main.Backend development, Develop architecture & diagrams</td>
    </tr>
    <tr>
        <td><a href="https://github.com/nah-y">Nahidha</a></td>
        <td>main.Backend development, Documentation</td>
    </tr>
    <tr>
        <td><a href="https://github.com/Jaaaxe">Yasmeen</a></td>
        <td>Frontend development, Frontend project management</td>
    </tr>
    <tr>
        <td><a href="https://github.com/notshaffan">Shaffan</a></td>
        <td>System UI/UX design, Testing, Frontend design</td>
    </tr>
    <tr>
        <td><a href="https://github.com/Hsain-r">Hussain</a></td>
        <td>Frontend development, Documentation</td>
    </tr>
</table>

<div>
  <h6 style="color: #58a6ff;">University of West of England [Villa College] | BSc (HONS) Computer Science - Jan 2019 | Enterprise Systems Development (UFCF85-30-3) | GA</h6>
</div>
