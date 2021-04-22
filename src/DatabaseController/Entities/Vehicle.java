package DatabaseController.Entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "vehicles")
public class Vehicle {
    @DatabaseField(columnName = "ID",generatedId = true)
    public Integer ID;
    @DatabaseField
    public String Manufacturer;
    @DatabaseField
    public int Year;

    @DatabaseField
    public String LicensePlateNo;
    @DatabaseField
    public String Color;
}
