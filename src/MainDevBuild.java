import Backend.BackendContext;
import Database.Entities.User;

import java.sql.SQLException;
import java.util.Scanner;

public class MainDevBuild {
    public static void main(String[] args) throws SQLException {
        System.out.println("Starting application...");
        System.out.println("Connecting to database...");


        var context = new BackendContext("jdbc:sqlserver://localhost:1433;databaseName=BLANKDB;user=sa;password=QuidEst");

        User sam = null;

        // Step 1
        System.out.println("Creating new user");
        context.User.RegisterNewUser("Sam", "Ramirez", "Arkangel", "OraS1m$1");

        // Step 2
        System.out.println("Assigining RC2FA");
        sam = context.User.GetByUsernameAndPassword("Arkangel", "OraS1m$1");
        System.out.println(sam.AssignRC2FA());
        context.DbContext.Users.update(sam);

        // Step 3
        System.out.println("Verifying Rolling code\n");
        sam = context.User.GetByUsernameAndPassword("Arkangel", "OraS1m$1");
        var s = new Scanner(System.in);
        var code = s.nextLine();
        System.out.println("Code Valid : " + sam.ConfirmTOTP(code));

    }
}
