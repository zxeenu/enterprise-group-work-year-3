package DatabaseController.Entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


public class Driver extends User {
    @DatabaseField(foreignAutoRefresh = true, foreign = true, foreignAutoCreate = true)
    public Vehicle Vehicle;
}
