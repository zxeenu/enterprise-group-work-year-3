package Backend;

import Database.DatabaseContext;
import Database.Entities.Trip;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class BackendContext {

    public DatabaseContext DbContext;
    public TripModule Trip;
    public UserModule User;

    public BackendContext(String ConnectionString) {
        this.ConnectToDatabase(ConnectionString);
        this.User = new UserModule();
        this.Trip = new TripModule();
    }

    /**
     * Connect to database
     * @param ConnectionString Connection String to use
     */
    public void ConnectToDatabase(String ConnectionString) {
        try {
            this.DbContext = new DatabaseContext(ConnectionString);
        } catch (SQLException throwables) {
            System.out.println("Failed to connect to database");
            throwables.printStackTrace();
        }
    }

    /**
     * Drops all the tables. Only use for rapid development. Do not use in production build.
     * @throws SQLException Standard ORMLite Exception
     */
    private void DropAllTables() throws SQLException {
        for (var x : DbContext.GetDAOList()) {
            TableUtils.dropTable(x, false);
        }
        DbContext.CheckAndRebuildDb();
    }


}
