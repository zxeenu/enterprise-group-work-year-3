package main.Database.Entities;

import main.Common.Shared;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

@DatabaseTable(tableName = "Trips")
public class Trip {

    public Trip() throws SQLException {
        this.TripComplete = false;
        this.CreationTime = Calendar.getInstance().getTime();
        Shared.DbContext.Trips.assignEmptyForeignCollection(this, "RejectionReasons");
    }

    @DatabaseField(generatedId = true, columnName = "ID")
    public Integer ID;
    @DatabaseField(foreignAutoRefresh = true, foreign = true, foreignAutoCreate = true)
    public User Customer;
    @DatabaseField
    public boolean TripComplete;

    @DatabaseField(dataType = DataType.DATE_STRING, format = "yyyy-MM-dd HH:mm:ss")
    public java.util.Date CreationTime;


    @DatabaseField(foreignAutoRefresh = true, foreign = true, foreignAutoCreate = true)
    public User Driver;

    @DatabaseField(foreignAutoRefresh = true, foreign = true, foreignAutoCreate = true)
    public Vehicle Vehicle;

    @DatabaseField
    public String StartName;
    @DatabaseField
    public double StartLattitude;
    @DatabaseField
    public double StartLongtitude;

    @DatabaseField
    public String EndName;
    @DatabaseField
    public double EndLattitude;
    @DatabaseField
    public double EndLongtitude;
    @DatabaseField
    public double Distance;

    @ForeignCollectionField(columnName = "RejectionReasons", eager = false)
    public ForeignCollection<TripRejectionReason> RejectionReasons;

    public static class TripState {
        public static int REJECTED = -2;
        public static int CANCELLED = -1;
        public static int AWAITING_PICKUP = 0;
        public static int IN_PROGRESS = 1;
        public static int COMPLETE = 2;
    }

    @DatabaseField
    public int State = TripState.AWAITING_PICKUP;

    @DatabaseField
    public float PaidAmount;
    public User getDriver() {
        return this.Driver;
    }

//    public enum TripState {
//        AWAITING_PICKUP,
//        IN_PROGRESS,
//        COMPLETE
//    }

//    @ForeignCollectionField
//    public Collection<Waypoint> WayPoints;

//    @DatabaseField(defaultValue = "1")
////    1. UNASSIGNED
////    2. ACCEPTED
////    3. REJECTED
//    public int JobStatus;


//    /**
//     * Use class enum
//     */
//    public static enum JobStatus {
//        UNASSIGNED(1),  ACCEPTED(2), REJECTED(3);
//
//        public final int UserCode;
//        JobStatus(int i) {
//            UserCode = i;
//        }
//    }

    public Trip(Integer ID, User customer, boolean tripComplete, Date creationTime, User driver, main.Database.Entities.Vehicle vehicle, String startName, double startLattitude, double startLongtitude, String endName, double endLattitude, double endLongtitude, double distance, int state, float paidAmount) {
        this.ID = ID;
        Customer = customer;
        TripComplete = tripComplete;
        CreationTime = creationTime;
        Driver = driver;
        Vehicle = vehicle;
        StartName = startName;
        StartLattitude = startLattitude;
        StartLongtitude = startLongtitude;
        EndName = endName;
        EndLattitude = endLattitude;
        EndLongtitude = endLongtitude;
        Distance = distance;
        State = state;
        PaidAmount = paidAmount;
    }


    //region Getters and Setters

    public Integer getID() {
        return ID;
    }

    public User getCustomer() {
        return Customer;
    }

    public boolean isTripComplete() {
        return TripComplete;
    }

    public Date getCreationTime() {
        return CreationTime;
    }

    public String getGetRejectionReasons() {
        String reason = "";
        try {
            Shared.DbContext.Trips.refresh(this);
            for (var d : RejectionReasons) {
                reason += d.RejectionReason ;
            }
        } catch (Exception e) {

        }
        return reason;
    }

    public main.Database.Entities.Vehicle getVehicle() {
        return Vehicle;
    }

    public String getStartName() {
        return StartName;
    }

    public double getStartLattitude() {
        return StartLattitude;
    }

    public double getStartLongtitude() {
        return StartLongtitude;
    }

    public String getEndName() {
        return EndName;
    }

    public double getEndLattitude() {
        return EndLattitude;
    }

    public double getEndLongtitude() {
        return EndLongtitude;
    }

    public double getDistance() {
        return Distance;
    }

    public float getPaidAmount() { return PaidAmount; }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setCustomer(User customer) {
        Customer = customer;
    }

    public void setTripComplete(boolean tripComplete) {
        TripComplete = tripComplete;
    }

    public void setCreationTime(Date creationTime) {
        CreationTime = creationTime;
    }

    public void setDriver(User driver) {
        Driver = driver;
    }

    public void setVehicle(main.Database.Entities.Vehicle vehicle) {
        Vehicle = vehicle;
    }

    public void setStartName(String startName) {
        StartName = startName;
    }

    public void setStartLattitude(double startLattitude) {
        StartLattitude = startLattitude;
    }

    public void setStartLongtitude(double startLongtitude) {
        StartLongtitude = startLongtitude;
    }

    public void setEndName(String endName) {
        EndName = endName;
    }

    public void setEndLattitude(double endLattitude) {
        EndLattitude = endLattitude;
    }

    public void setEndLongtitude(double endLongtitude) {
        EndLongtitude = endLongtitude;
    }

    public void setDistance(double distance) {
        Distance = distance;
    }

    public void setPaidAmount(float paidAmount) {
        PaidAmount = paidAmount;
    }

    public void setRejectionReasons(ForeignCollection<TripRejectionReason> rejectionReasons) {
        RejectionReasons = rejectionReasons;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    //endregion
}
