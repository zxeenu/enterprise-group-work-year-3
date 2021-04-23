import Backend.BackendContext;

import java.sql.SQLException;
import java.util.Scanner;

public class MainDevBuild {
    public static void main(String[] args) throws SQLException {
        System.out.println("Starting application...");
        System.out.println("Connecting to database...");


//        var BEContext = new BackendContext("jdbc:sqlserver://localhost:1433;databaseName=BLANKDB;user=sa;password=QuidEst");
//
//
//        // Step 1 : Create a test user
//        var sam = BEContext.User.RegisterNewUser("Sam", "Ramirez", "Arkangel", "OraS1m$1");
//
//        // Step 2 : Assign a new TOTP Secret to the test user
//        System.out.println("New TOTP Secret : " + sam.AssignTOTPSecret());
//        BEContext.DbContext.Users.update(sam);
//
//        // Step 3 : Confirm that its working correctly
//        var s = new Scanner(System.in);
//        var code = s.nextLine();
//        System.out.println("Code Valid : " + sam.ConfirmTOTP(code));

    }
}
