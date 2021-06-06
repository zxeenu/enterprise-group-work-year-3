package Backend;

import Common.Logger;
import Common.Shared;
import Database.DatabaseContext;
import Database.Entities.Trip;
import WebUI.Session;
import WebUI.SessionMonitor;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;

public class BackendContext implements SessionMonitor{

    public DatabaseContext DbContext;
    public TripModule Trip;
    public UserModule User;

    public ArrayList<Session> ActiveSessions;

    @Override
    public void SessionExpired(Session s) {}

    @Override
    public void SessionExtended(Session s) {}

    @Override
    public void SessionTerminated(Session s) {
        this.ActiveSessions.remove(s);
    }

    public BackendContext(String ConnectionString) {
        this.ConnectToDatabase(ConnectionString);
        this.User = new UserModule();
        this.Trip = new TripModule();
        this.Trip.StartTripPoolHandler();
        Shared.BeContext = this;
        Shared.DbContext = this.DbContext;
    }



    /**
     * Connect to database
     * @param ConnectionString Connection String to use
     */
    public void ConnectToDatabase(String ConnectionString) {
        try {
            this.DbContext = new DatabaseContext(ConnectionString);
        } catch (SQLException throwables) {
            Logger.Instance.Add("Failed to connect to database", Logger.LogLevels.CRITICAL);
            throwables.printStackTrace();
        }
    }

    /**
     * Drops all the tables. Only use for rapid development. Do not use in production build.
     * @throws SQLException Standard ORMLite Exception
     */
    public void DropAllTables() throws SQLException {
        for (var x : DbContext.GetDAOList()) {
            TableUtils.dropTable(x, false);
        }
        DbContext.CheckAndRebuildDb();
    }



}
