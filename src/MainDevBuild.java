import DatabaseController.DatabaseContext;
import DatabaseController.Entities.Driver;
import DatabaseController.Entities.Trip;
import DatabaseController.Entities.Vehicle;

import java.sql.SQLException;

public class MainDevBuild {
    public static void main(String[] args) throws SQLException {
        System.out.println("Starting application...");
        System.out.println("Connecting to database...");


        var cont = new DatabaseContext("jdbc:sqlserver://localhost:1433;databaseName=BLANKDB;user=sa;password=QuidEst");



        var driver = new Driver();
        driver.FirstName = "Sam";
        driver.LastName = "Ramirez";
        driver.SetUsername("Arkangel");
        driver.SetPassword("OraS1m$1");

        cont.DriverDao.create(driver);

        var trip = new Trip();
        trip.Driver = driver;
        cont.TripsDao.create(trip);

        cont.DriverDao.refresh(cont.TripsDao.iterator().first().Driver);
        System.out.println(cont.TripsDao.iterator().first().Driver.FirstName);





    }
}
