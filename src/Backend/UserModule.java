package Backend;

import Database.DatabaseContext;
import Database.Entities.Role;
import Database.Entities.User;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserModule {
    private DatabaseContext DbContext;

    public UserModule(DatabaseContext db) {
        this.DbContext = db;
    }

    public User GetByUsername(String u) {
        var useriterator = DbContext.Users.iterator();
        while (useriterator.hasNext()) {
            var cu = useriterator.next();
            if (cu.ConfirmUsername(u)) return cu;
        }
        return null;
    }

    public User GetByPassword(String p) {
        var useriterator = DbContext.Users.iterator();
        while (useriterator.hasNext()) {
            var cu = useriterator.next();
            if (cu.ConfirmPassword(p)) return cu;
        }
        return null;
    }

    public User GetByUsernameAndPassword(String u, String p) {
        var useriterator = DbContext.Users.iterator();
        while (useriterator.hasNext()) {
            var cu = useriterator.next();

            if (cu.ConfrimUsernameAndPassword(u, p)) return cu;
        }
        return null;
    }

    public ArrayList<User> GetUsersByRole(Role r) {
        var rl = new ArrayList<User>();
        var useriterator = DbContext.Users.iterator();
        while (useriterator.hasNext()) {
            var cu = useriterator.next();
            if (cu.Role == r ) rl.add(cu);
        }
        return rl;
    }

    public User RegisterNewUser(String firstname, String lastname, String username, String password) throws SQLException {
        var newuser = new User();
        newuser.FirstName = firstname;
        newuser.LastName = lastname;
        newuser.SetUsername(username);
        newuser.SetPassword(password);
        DbContext.Users.create(newuser);
        return newuser;
    }

    public ArrayList<User> GetAllUsers() {
        var rlist = new ArrayList<User>();
        var iterator = this.DbContext.Users.iterator();
        while (iterator.hasNext()) {
            rlist.add(iterator.next());
        }
        return rlist;
    }

    public void UpdateUser(User u) throws SQLException {
        this.DbContext.Users.update(u);
    }

    public void DeleteUser(User u) throws SQLException {
        this.DbContext.Users.delete(u);
    }
}
