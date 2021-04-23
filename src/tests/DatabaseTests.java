package tests;

import Common.Security;
import Database.DatabaseContext;
import Database.Entities.*;
import com.j256.ormlite.table.TableUtils;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class DatabaseTests {

    DatabaseContext context = null;
	String connectionString = "";

    public DatabaseTests() throws SQLException, InterruptedException {
        System.out.println("Running database tests...");
		this.connectionString = "jdbc:sqlserver://localhost:1433;databaseName=JUNITDB;user=sa;password=QuidEst";
        this.context = new DatabaseContext(connectionString);
        for (var x : this.context.GetDAOList()) {
            TableUtils.dropTable(x, false);
        }
        context.CheckAndRebuildDb();

    }

    @Test
    @DisplayName("Login Test Database Level")
    public void LoginTestDatabaseLevel() throws SQLException {
        AddDummyUser();
        assertTrue(User.ValidateLogin("johnnys", "johnsm123", context.Users.iterator()) != null);
    }

    public void AddDummyUser() throws SQLException {
        var NewUser = new User();
        NewUser.FirstName = "John";
        NewUser.LastName = "Smith";
        NewUser.SetUsername("johnnys");
        NewUser.SetPassword("johnsm123");
        context.Users.create(NewUser);
    }

    @Test
    public void PersistenceTest() throws Exception {
        AddDummyUser();
        context = null;
        context = new DatabaseContext(this.connectionString);
        assertTrue((context.Users.countOf() > 0));
    }


    @Test
    public void ManyToOneWriteTest() throws SQLException {
        var dummydriver = new User();
        var dummy1 = new Trip() {{ Driver = dummydriver; }};
        var dummy2 = new Trip() {{ Driver = dummydriver; }};
        var dummy3 = new Trip() {{ Driver = dummydriver; }};
        context.Users.create(dummydriver);
        context.Trips.create(dummy1);
        context.Trips.create(dummy2);
        context.Trips.create(dummy3);
    }

    public void OneToOneReadAndWriteTest() throws SQLException {
        var user = new User();
        var role = new Role();
        role.RoleName = "Test Role";
        user.Role = role;

        context.Roles.create(role);
        context.Users.create(user);

        var read_data = context.Users.iterator().first().Role.RoleName;
        assertEquals("Test Role", read_data);
    }

    @Test
    public void ManyToOneReadTest() throws SQLException, InterruptedException {
        var dummydriver = new User();
        dummydriver.FirstName = "John";
        dummydriver.LastName = "Smith";
        var dummy1 = new Trip();
        dummy1.Driver = dummydriver;
        context.Users.create(dummydriver);
        context.Trips.create(dummy1);
        assertEquals("John", context.Trips.iterator().first().Driver.FirstName);
    }

    @Test
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
}