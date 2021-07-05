package Tests;

import main.Backend.BackendContext;
import main.Common.Security;
import main.Common.Shared;
import main.Database.Entities.*;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * This test will verify that the back end components of our system
 * works as intended
 */
@DisplayName("main.Database Core Tests")
public class BackendCodeTests {

    BackendContext BEContext;
	String connectionString = "";

    /**
     * Constructor
     * @throws SQLException
     * @throws InterruptedException
     */
    public BackendCodeTests() throws SQLException, InterruptedException {
        System.out.println("Running database tests...");
        this.connectionString = Const.JUnitConnectionString;
		this.BEContext = new BackendContext(this.connectionString);
		Shared.BeContext = this.BEContext;
		Shared.DbContext = BEContext.DbContext;
    }

    /**
     * This function will create a dummy user
     * @return Dummy user that was created
     * @throws SQLException
     */
    public User AddDummyUser() throws SQLException {
        return BEContext.User.RegisterNewUser("John", "Smith", "jsmith", "OraS1m$1", User.UserType.ADMIN);
    }

    /**
     * This function will just call the add dummy
     * users function again. I dont know exactly
     * why I wrote this function to only have it call
     * another function
     * @throws SQLException
     */
    @Test
    public void RegisterUsers() throws SQLException {
        AddDummyUser();
    }

    /**
     * This function will make sure that the hashing
     * works consistently. This is important to make
     * that logging in and out works properly
     */
    @Test
    @DisplayName("Hashing Consistency")
    public void HashingConsistency() {
        var data = Security.GenerateRandomHex(20);
        var salt = Security.GenerateRandomHex(5);
        var result_a = Security.HashString(data + salt);
        var result_b = Security.HashString(data + salt);
        assertEquals(result_a, result_b);
    }

    /**
     * This function will make sure that logging in works
     * from the function in the User class
     */
    @Test
    public void LoginTestClassLevel() {
        var User = new User();
        User.SetUsername("ark");
        User.SetPassword("ark");
        assertTrue(User.ConfrimUsernameAndPassword("ark", "ark"));
    }

    /**
     * This function will make sure that logging in works from the
     * function in the backend context
     * @throws SQLException
     */
    @Test
    public void LoginTestBackendContextLevel() throws SQLException {
        AddDummyUser();
        assertNotNull(BEContext.User.GetByUsernameAndPassword("jsmith", "OraS1m$1"));
    }

    /**
     * This function tests if the function that fetches users from the database
     * based on their role
     * @throws SQLException
     */
    @Test
    public void FetchUserByRole() throws SQLException {
        var u = AddDummyUser();
        var nrole = new Role();
        nrole.RoleName = "Test Role";
        u.Role = nrole;
        BEContext.DbContext.Users.update(u);
        assertNotNull(BEContext.User.GetUsersByRole(nrole));
    }
}