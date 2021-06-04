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

    @DatabaseField
//    1. Administrator
//    2. Driver
//    3. Customer
    public int UserClassCode;

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

    /**
     * Assign a TOTP Secret Key
     * @return
     */
    public String AssignTOTPSecret() {
        this.TOTPSecret = Common.Security.GenerateTOTPSecret();
        return TOTPSecret;
    }

    public boolean ConfirmHash(String h) {
        return h.equals(this.HashPassword);
    }



    /**
     * Confirm that the provided TOTP code is valid. If the TOTP is not setup, will return false
     * @param code Code to match
     * @return Boolean : Indicates if the code matches
     */
    public boolean ConfirmTOTP(String code) {
        if (this.TOTPSecret.isBlank()) {
            return false;
        }
        return Common.Security.VerifyTOTPCode(this.TOTPSecret, code);
    }

    /**
     * Matches a username and password from an iterator
     * @param username Username to match
     * @param password Password to match
     * @param c iterator to use
     * @return User that matches the provided login. Null if not found
     */
    public static User ValidateLogin(String username, String password, CloseableIterator c) {
        while (c.hasNext()) {
            var cu = (User)c.next() ;
            if (cu.ConfrimUsernameAndPassword(username, password)) {
                return cu;
            }
        }
        return null;
    }



    /**
     * Use class enum
     */
    public static enum UserType {
        ADMIN(1),  DRIVER(2), CUSTOMER(3);

        public final int UserCode;
        UserType(int i) {
            UserCode = i;
        }
    }

}
