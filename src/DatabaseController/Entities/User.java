package DatabaseController.Entities;

import DatabaseController.Common.Security;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigInteger;
import java.security.MessageDigest;

@DatabaseTable(tableName = "UserAccounts")
public class User {
    @DatabaseField(columnName = "ID",generatedId = true)
    public Integer ID;

    @DatabaseField
    public String FirstName;

    @DatabaseField
    public String LastName;

    @DatabaseField
    public String HashUsername;

    @DatabaseField
    public String HashPassword;

    @DatabaseField
    public String Salt;

    public User() {
        this.Salt = Security.GenerateRandomHex(16);
    }

    public void SetPassword(String p) {
        this.HashPassword = Security.HashString(p + this.Salt);
    }

    public void SetUsername(String u) {
        this.HashUsername = Security.HashString(u + this.Salt);
    }

    public boolean ConfirmPassword(String p) {
        return Security.HashString(p + this.Salt).equals(this.HashPassword);
    }

    public boolean ConfirmUsername(String u) {
        return Security.HashString(u + this.Salt).equals(this.HashUsername);
    }

    public boolean ConfrimUsernameAndPassword(String u, String p) {
        return this.ConfirmUsername(u) & this.ConfirmPassword(p);
    }


}
