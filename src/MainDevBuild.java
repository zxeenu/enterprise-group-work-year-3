import DatabaseController.Controller;
import DatabaseController.Entities.User;

import java.sql.SQLException;

public class MainDevBuild {
    public static void main(String[] args) throws SQLException {
        System.out.println("Starting application...");
        System.out.println("Connecting to database...");


        var cont = new Controller("jdbc:sqlserver://localhost:1433;databaseName=BLANKDB;user=sa;password=QuidEst");

        var newUser = new User();
        newUser.FirstName = "Sam";
        newUser.LastName = "Ramirez";
        newUser.SetUsername("Arkangel");
        newUser.SetPassword("OraS1m$1");

        cont.UserAccountDao.create(newUser);


    }
}
