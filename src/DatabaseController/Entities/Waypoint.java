package DatabaseController.Entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable
public class Waypoint {
    @DatabaseField(generatedId = true, columnName = "ID")
    public int ID;

    @DatabaseField
    public double Lattitude;

    @DatabaseField
    public double Longitude;
}
