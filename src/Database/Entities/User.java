package Database.Entities;

import Common.Security;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

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

    @DatabaseField
    public String TOTPSecret;

    @DatabaseField(foreignAutoRefresh = true, foreign = true, foreignAutoCreate = true)
    public Vehicle PrimaryVehicle;

    @DatabaseField
    public String CreditCardNumber;

    @DatabaseField(foreignAutoRefresh = true, foreign = true, foreignAutoCreate = true)
    public Role Role;

    public void setFirstName(String fs) {
        this.FirstName = fs;
    }

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

    public String AssignRC2FA() {
        this.TOTPSecret = Common.Security.GenerateTOTPSecret();
        return TOTPSecret;
    }

    public boolean ConfirmTOTP(String code) {
        if (this.TOTPSecret.isBlank()) {
            return false;
        }
        return Common.Security.VerifyTOTPCode(this.TOTPSecret, code);
    }

    public static User ValidateLogin(String username, String password, CloseableIterator c) {
        while (c.hasNext()) {
            var cu = (User)c.next() ;
            if (cu.ConfrimUsernameAndPassword(username, password)) {
                return cu;
            }
        }
        return null;
    }
}
