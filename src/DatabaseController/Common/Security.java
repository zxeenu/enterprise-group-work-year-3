package DatabaseController.Common;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;

public class Security {
    public static String HashString(String c) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(c.getBytes());
            return new BigInteger(messageDigest.digest()).toString(16);
        } catch (Exception e) {

        }
        return "";
    }

    public static String GenerateRandomHex(int numchars){
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < numchars){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, numchars);
    }
}
