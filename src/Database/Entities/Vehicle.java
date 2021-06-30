package Database.Entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Vehicles")
public class Vehicle {
    @DatabaseField(columnName = "ID",generatedId = true)
    public Integer ID;
    @DatabaseField
    public String Manufacturer;
    @DatabaseField
    public String LicensePlateNo;
    @DatabaseField
    public String Color;

    public Vehicle() {
    }

    public Vehicle(String manufacturer, String licensePlateNo, String color) {
        Manufacturer = manufacturer;
        LicensePlateNo = licensePlateNo;
        Color = color;
    }
}
