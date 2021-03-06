package main.Database.Entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "Waypoints")
public class WaypointDB {
    @DatabaseField(generatedId = true, columnName = "ID")
    public int ID;

    @DatabaseField
    public double Lattitude;

    @DatabaseField
    public double Longitude;
}
