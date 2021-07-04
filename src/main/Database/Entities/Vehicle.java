package main.Database.Entities;

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

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getLicensePlateNo() {
        return LicensePlateNo;
    }

    public void setLicensePlateNo(String licensePlateNo) {
        LicensePlateNo = licensePlateNo;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }
}
