package main.Database.Entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "TripRejectionReasons")
public class TripRejectionReason {

    public TripRejectionReason() {

    }

    @DatabaseField(foreign = true)
    public Trip Trip;

    @DatabaseField(columnName = "ID", generatedId = true)
    public int ID;

    @DatabaseField(foreignAutoRefresh = true, foreign = true, foreignAutoCreate = true)
    public User Driver;

    @DatabaseField(defaultValue = "NA")
    public String RejectionReason;

    public main.Database.Entities.Trip getTrip() {
        return Trip;
    }

    public void setTrip(main.Database.Entities.Trip trip) {
        Trip = trip;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public User getDriver() {
        return Driver;
    }

    public void setDriver(User driver) {
        Driver = driver;
    }

    public String getRejectionReason() {
        return RejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        RejectionReason = rejectionReason;
    }
}
