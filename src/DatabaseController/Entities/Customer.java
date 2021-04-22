package DatabaseController.Entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


public class Customer extends User {
    @DatabaseField
    public String CreditCardNo;
}
