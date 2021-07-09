package main.Database.Entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Roles")
public class Role {

    @DatabaseField(columnName = "ID", generatedId = true)
    public Integer ID;
    @DatabaseField
    public String RoleName;
    @DatabaseField
    public String AccessCode;
}
