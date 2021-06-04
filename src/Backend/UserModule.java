package Backend;

import Common.Shared;
import Database.Entities.Role;
import Database.Entities.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserModule {

    public UserModule() { }

    /**
     * This function gets a user by username. It will return null if the user was not found
     * @param u User
     * @return The user. Null if not found
     */
    public User GetByUsername(String u) {
        var useriterator = Common.Shared.DbContext.Users.iterator();
        while (useriterator.hasNext()) {
            var cu = useriterator.next();
            if (cu.ConfirmUsername(u)) return cu;
        }
        return null;
    }

    /**
     * This function gets a user by password. It will return null if the user was not found.
     * @param p Password
     * @return The user. Null if not found
     */
    public User GetByPassword(String p) {
        var useriterator = Common.Shared.DbContext.Users.iterator();
        while (useriterator.hasNext()) {
            var cu = useriterator.next();
            if (cu.ConfirmPassword(p)) return cu;
        }
        return null;
    }

    /**
     * This function will get a user by their password hash. It will return null if no user was found
     * @param h Hash
     * @return The user. Null if not found
     */
    public User GetByPasswordHash(String h) {
        var useriterator = Shared.DbContext.Users.iterator();
        while (useriterator.hasNext()) {
            var cu = useriterator.next();
            if (cu.ConfirmHash(h)) return cu;
        }
        return null;
    }

    /**
     * Gets all the users by type. The types includes Administrator, Customer and Driver.
     * @param ur Usertype
     * @return A list of users with the type passed in
     */
    private ArrayList<User> GetUsersByType(User.UserType ur) {
        var rl = new ArrayList<User>();
        var iterator = Shared.DbContext.Users.iterator();
        while (iterator.hasNext()) {
            var cu = iterator.next();
            if (cu.UserClassCode == ur.UserCode) rl.add(cu);
        }
        return rl;
    }

    /**
     * Gets all the drivers
     * @return ArrayList of all the drivers
     */
    public ArrayList<User> GetAllDrivers() {
        return this.GetUsersByType(User.UserType.DRIVER);
    }

    /**
     * Gets all the Administrators
     * @return ArrayList of all the Administrators
     */
    public ArrayList<User> GetAllAdministrators() {
        return this.GetUsersByType(User.UserType.ADMIN);
    }

    /**
     * Gets all the Customers
     * @return ArrayList of all the customers
     */
    public ArrayList<User> GetAllCustomers() {
        return this.GetUsersByType(User.UserType.CUSTOMER);
    }

    /**
     * Gets the user by the username and password. Use this for extra security
     * @param u Username
     * @param p Password
     * @return User that matches the username and password. Null if the user is not found
     */
    public User GetByUsernameAndPassword(String u, String p) {
        var useriterator = Common.Shared.DbContext.Users.iterator();
        while (useriterator.hasNext()) {
            var cu = useriterator.next();

            if (cu.ConfrimUsernameAndPassword(u, p)) return cu;
        }
        return null;
    }

    /**
     * Gets the users by role
     * @param r Role to match
     * @return ArrayList of matching users
     */
    public ArrayList<User> GetUsersByRole(Role r) {
        var rl = new ArrayList<User>();
        var useriterator = Common.Shared.DbContext.Users.iterator();
        while (useriterator.hasNext()) {
            var cu = useriterator.next();
            if (cu.Role == r ) rl.add(cu);
        }
        return rl;
    }

    /**
     * Register a new user
     * @param firstname First name
     * @param lastname Last name
     * @param username Username (Will be hashed)
     * @param password Password (Will be hashed)
     * @param uc User type
     * @return The user that got created
     * @throws SQLException Standard ORMLite Exception
     */
    public User RegisterNewUser(String firstname, String lastname, String username, String password, User.UserType uc) throws SQLException {
        var newuser = new User();
        newuser.FirstName = firstname;
        newuser.LastName = lastname;
        newuser.SetUsername(username);
        newuser.SetPassword(password);
        newuser.UserClassCode = uc.UserCode;
        Common.Shared.DbContext.Users.create(newuser);
        return newuser;
    }

    /**
     * Gets all the users registered in the database
     * @return ArrayList of all users
     */
    public ArrayList<User> GetAllUsers() {
        var rlist = new ArrayList<User>();
        var iterator = Common.Shared.DbContext.Users.iterator();
        while (iterator.hasNext()) {
            rlist.add(iterator.next());
        }
        return rlist;
    }

    /**
     * Updates the user record in the database. Standard CRUD Operation
     * @param u User to be updated
     * @throws SQLException Standard ORMLite Exception
     */
    public void UpdateUser(User u) throws SQLException {
        Common.Shared.DbContext.Users.update(u);
    }

    /**
     * Deletes the user record in the database. Standard CRUD Operation
     * @param u Use to be deleted
     * @throws SQLException Standard ORMLite Exception
     */
    public void DeleteUser(User u) throws SQLException {
        Common.Shared.DbContext.Users.delete(u);
    }
}
