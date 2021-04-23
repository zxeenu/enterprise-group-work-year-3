package Backend;

import Database.DatabaseContext;

import java.sql.SQLException;

public class BackendContext {

    public DatabaseContext DbContext;

    public UserModule User;

    public BackendContext(String ConnectionString) {
        this.ConnectToDatabase(ConnectionString);
        this.User = new UserModule(DbContext);
    }

    public void ConnectToDatabase(String ConnectionString) {
        try {
            this.DbContext = new DatabaseContext(ConnectionString);
        } catch (SQLException throwables) {
            System.out.println("Failed to connect to database");
            throwables.printStackTrace();
        }
    }



}
