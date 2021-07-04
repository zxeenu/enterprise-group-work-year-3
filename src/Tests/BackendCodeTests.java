package Tests;

import main.Backend.BackendContext;
import main.Common.Security;
import main.Common.Shared;
import main.Database.Entities.*;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.sql.SQLException;

import static org.junit.Assert.*;

@DisplayName("main.Database Core Tests")
public class BackendCodeTests {

    BackendContext BEContext;
	String connectionString = "";


    public BackendCodeTests() throws SQLException, InterruptedException {
        System.out.println("Running database tests...");
        this.connectionString = Const.JUnitConnectionString;
		this.BEContext = new BackendContext(this.connectionString);
		Shared.BeContext = this.BEContext;
		Shared.DbContext = BEContext.DbContext;
    }


    public User AddDummyUser() throws SQLException {
        return BEContext.User.RegisterNewUser("John", "Smith", "jsmith", "OraS1m$1", User.UserType.ADMIN);
    }
    // pee pee
    @Test
    public void RegisterUsers() throws SQLException {
        AddDummyUser();
    }

    @Test
    @DisplayName("Hashing Consistency")
    public void HashingConsistency() {
        var data = Security.GenerateRandomHex(20);
        var salt = Security.GenerateRandomHex(5);
        var result_a = Security.HashString(data + salt);
        var result_b = Security.HashString(data + salt);
        assertEquals(result_a, result_b);
    }


    @Test
    public void LoginTestClassLevel() {
        var User = new User();
        User.SetUsername("ark");
        User.SetPassword("ark");
        assertTrue(User.ConfrimUsernameAndPassword("ark", "ark"));
    }

    @Test
    public void LoginTestBackendContextLevel() throws SQLException {
        AddDummyUser();
        assertNotNull(BEContext.User.GetByUsernameAndPassword("jsmith", "OraS1m$1"));
    }

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