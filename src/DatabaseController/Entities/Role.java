package DatabaseController.Entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Role {

    @DatabaseField(columnName = "ID", id = true)
    public Integer ID;
    @DatabaseField
    public String RoleName;
    @DatabaseField
    public String AccessCode;
}
