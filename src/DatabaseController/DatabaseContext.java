package DatabaseController;


import DatabaseController.Entities.*;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.jdbc.JdbcDatabaseConnection;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseContext {

    public DatabaseContext(String c) throws SQLException {
        Connection = new JdbcConnectionSource(c);
        this.AdministratorDao = DaoManager.createDao(Connection, Administrator.class);
        this.CustomerDao = DaoManager.createDao(Connection, Customer.class);
        this.DriverDao = DaoManager.createDao(Connection, Driver.class);
        this.VehicleDao = DaoManager.createDao(Connection, Vehicle.class);
        this.RolesDao = DaoManager.createDao(Connection, Role.class);
        this.WaypointsDao = DaoManager.createDao(Connection, Waypoint.class);
        this.TripsDao = DaoManager.createDao(Connection, Trip.class);

        CheckAndRebuildDb();

    }

    JdbcConnectionSource Connection;

    public void CheckAndRebuildDb() throws SQLException {
        if (!AdministratorDao.isTableExists()) TableUtils.createTable(Connection, Administrator.class);
        if (!CustomerDao.isTableExists()) TableUtils.createTable(Connection, Customer.class);
        if (!DriverDao.isTableExists()) TableUtils.createTable(Connection, Driver.class);
        if (!VehicleDao.isTableExists()) TableUtils.createTable(Connection, Vehicle.class);
        if (!RolesDao.isTableExists()) TableUtils.createTable(Connection, Role.class);
        if (!WaypointsDao.isTableExists()) TableUtils.createTable(Connection, Waypoint.class);
        if (!TripsDao.isTableExists()) TableUtils.createTable(Connection, Trip.class);
    }

    public Dao<Administrator, String> AdministratorDao = null;
    public Dao<Customer, String> CustomerDao = null;
    public Dao<Driver, String> DriverDao = null;
    public Dao<Vehicle, String> VehicleDao = null;
    public Dao<Role, String> RolesDao = null;
    public Dao<Waypoint, String> WaypointsDao = null;
    public Dao<Trip, String> TripsDao = null;

    public ArrayList<Dao<?, ?>> GetDAOList() {
        return new ArrayList<Dao<?, ?>>() {{
            add(AdministratorDao);
            add(CustomerDao);
            add(DriverDao);
            add(VehicleDao);
            add(RolesDao);
            add(WaypointsDao);
            add(TripsDao);
        }};
    }
}
