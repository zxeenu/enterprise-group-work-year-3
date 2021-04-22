package tests;

import DatabaseController.Common.Security;
import DatabaseController.DatabaseContext;
import DatabaseController.Entities.*;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

import javax.crypto.SecretKey;
import javax.naming.Context;
import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class BackendCodeTests {

    DatabaseContext context = null;
	String connectionString = "";

    public BackendCodeTests() throws SQLException, InterruptedException {
        System.out.println("Running database tests...");
		this.connectionString = "jdbc:sqlserver://localhost:1433;databaseName=JUNITDB;user=sa;password=QuidEst";
        this.context = new DatabaseContext(connectionString);
        for (var x : this.context.GetDAOList()) {
            TableUtils.dropTable(x, false);
        }
        context.CheckAndRebuildDb();

    }

    @Test
    public void LoginTestDatabaseLevel() throws SQLException {
        AddDummyUser();
        assertTrue(User.ValidateLogin("johnnys", "johnsm123", context.AdministratorDao.iterator()) != null);
    }

    public void AddDummyUser() throws SQLException {
        var NewUser = new Administrator();
        NewUser.FirstName = "John";
        NewUser.LastName = "Smith";
        NewUser.SetUsername("johnnys");
        NewUser.SetPassword("johnsm123");
        context.AdministratorDao.create(NewUser);
    }

    @Test
    public void PersistenceTest() throws Exception {
        AddDummyUser();
        context = null;
        context = new DatabaseContext(this.connectionString);
        assertTrue((context.AdministratorDao.countOf() > 0));
    }


    @Test
    public void ManyToOneWriteTest() throws SQLException {
        var dummydriver = new Driver();
        var dummy1 = new Trip() {{ Driver = dummydriver; }};
        var dummy2 = new Trip() {{ Driver = dummydriver; }};
        var dummy3 = new Trip() {{ Driver = dummydriver; }};
        context.DriverDao.create(dummydriver);
        context.TripsDao.create(dummy1);
        context.TripsDao.create(dummy2);
        context.TripsDao.create(dummy3);
    }

    public void OneToOneReadAndWriteTest() throws SQLException {
        var user = new Administrator();
        var role = new Role();
        role.RoleName = "Test Role";
        user.Role = role;

        context.RolesDao.create(role);
        context.AdministratorDao.create(user);

        var read_data = context.AdministratorDao.iterator().first().Role.RoleName;
        assertEquals("Test Role", read_data);
    }

    @Test
    public void ManyToOneReadTest() throws SQLException, InterruptedException {
        var dummydriver = new Driver();
        dummydriver.FirstName = "John";
        dummydriver.LastName = "Smith";
        var dummy1 = new Trip();
        dummy1.Driver = dummydriver;
        context.DriverDao.create(dummydriver);
        context.TripsDao.create(dummy1);
        assertEquals("John", context.TripsDao.iterator().first().Driver.FirstName);
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