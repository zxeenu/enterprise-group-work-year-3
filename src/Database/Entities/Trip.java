package Database.Entities;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Calendar;

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
    public String LocationName;

    @DatabaseField
    public double StartLattitude;
    @DatabaseField
    public double StartLongtitude;

    @DatabaseField
    public double EndLattitude;
    @DatabaseField
    public double EndLongtitude;

    @DatabaseField
    public double Distance;




    @DatabaseField
    public float PaidAmount;
    public User getDriver() {
        return this.Driver;
    }

//    @ForeignCollectionField
//    public Collection<Waypoint> WayPoints;
}
