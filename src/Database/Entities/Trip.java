package Database.Entities;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Calendar;
import java.util.Date;

@DatabaseTable
public class Trip {

    public Trip() {
        this.TripComplete = false;
        this.CreationTime = Calendar.getInstance().getTime();
    }

    @DatabaseField(generatedId = true, columnName = "ID")
    public Integer ID;

    @DatabaseField(foreignAutoRefresh = true, foreign = true, foreignAutoCreate = true)
    public User Customer;

    @DatabaseField
    public boolean TripComplete;


    @DatabaseField(dataType = DataType.DATE_STRING, format = "yyyy-MM-dd HH:mm:ss")
    public java.util.Date CreationTime;

    @DatabaseField(dataType = DataType.DATE_STRING, format = "yyyy-MM-dd HH:mm:ss")
    public java.util.Date BookingDate;

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

    public enum TripState {
        AWAITING_PICKUP,
        IN_PROGRESS,
        COMPLETE
    }

    public TripState State = TripState.AWAITING_PICKUP;

    @DatabaseField
    public float PaidAmount;
    public User getDriver() {
        return this.Driver;
    }

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

    public Date getBookingDate() {
        return BookingDate;
    }

    public Database.Entities.Vehicle getVehicle() {
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

    public float getPaidAmount() {
        return PaidAmount;
    }

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

    public void setBookingDate(Date bookingDate) {
        BookingDate = bookingDate;
    }

    public void setDriver(User driver) {
        Driver = driver;
    }

    public void setVehicle(Database.Entities.Vehicle vehicle) {
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
}
