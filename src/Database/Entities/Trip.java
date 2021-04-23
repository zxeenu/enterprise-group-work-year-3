package Database.Entities;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Trip {
    @DatabaseField(generatedId = true, columnName = "ID")
    public Integer ID;

    @DatabaseField(foreignAutoRefresh = true, foreign = true, foreignAutoCreate = true)
    public User Customer;

    @DatabaseField(dataType = DataType.DATE_STRING, format = "yyyy-MM-dd HH:mm:ss")
    public java.util.Date CreationTime;

    @DatabaseField(dataType = DataType.DATE_STRING, format = "yyyy-MM-dd HH:mm:ss")
    public java.util.Date BookingDate;

    @DatabaseField(dataType = DataType.DATE_STRING, format = "yyyy-MM-dd HH:mm:ss")
    public java.util.Date StartDate;

    @DatabaseField(foreignAutoRefresh = true, foreign = true, foreignAutoCreate = true)
    public User Driver;

    @DatabaseField(foreignAutoRefresh = true, foreign = true, foreignAutoCreate = true)
    public Vehicle Vehicle;

    public float PaidAmount;
    public User getDriver() {
        return this.Driver;
    }

//    @ForeignCollectionField
//    public Collection<Waypoint> WayPoints;
}
