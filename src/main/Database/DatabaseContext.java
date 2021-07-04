package main.Database;


import main.Database.Entities.*;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseContext {

    public DatabaseContext(String c) throws SQLException {
        Connection = new JdbcConnectionSource(c);
        this.Users = DaoManager.createDao(Connection, User.class);
        this.Vehicles = DaoManager.createDao(Connection, Vehicle.class);
        this.Roles = DaoManager.createDao(Connection, Role.class);
        this.Waypoints = DaoManager.createDao(Connection, WaypointDB.class);
        this.Trips = DaoManager.createDao(Connection, Trip.class);
        this.RejectionReasons = DaoManager.createDao(Connection, TripRejectionReason.class);
        CheckAndRebuildDb();

    }

    JdbcConnectionSource Connection;

    public void CheckAndRebuildDb() throws SQLException {
        if (!Users.isTableExists()) TableUtils.createTable(Connection, User.class);
        if (!Vehicles.isTableExists()) TableUtils.createTable(Connection, Vehicle.class);
        if (!Roles.isTableExists()) TableUtils.createTable(Connection, Role.class);
        if (!Waypoints.isTableExists()) TableUtils.createTable(Connection, WaypointDB.class);
        if (!Trips.isTableExists()) TableUtils.createTable(Connection, Trip.class);
        if (!RejectionReasons.isTableExists()) TableUtils.createTable(Connection, TripRejectionReason.class);
    }

    public Dao<User, String> Users = null;
    public Dao<Vehicle, String> Vehicles = null;
    public Dao<Role, String> Roles = null;
    public Dao<WaypointDB, String> Waypoints = null;
    public Dao<Trip, String> Trips = null;
    public Dao<TripRejectionReason, String> RejectionReasons = null;

    public ArrayList<Dao<?, ?>> GetDAOList() {
        return new ArrayList<Dao<?, ?>>() {{
            add(Users);
            add(Vehicles);
            add(Roles);
            add(Waypoints);
            add(Trips);
        }};
    }
}
