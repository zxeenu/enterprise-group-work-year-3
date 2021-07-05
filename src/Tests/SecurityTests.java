package Tests;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SecurityTests {

    /**
     * This function will make sure that the hashed strings dont
     * return null or blank strings
     */
    @Test
    public void HashStringNullOrBlankTest() {
        var res = main.Common.Security.HashString("BLANK");
        assertFalse(res.isBlank() || res.isEmpty() || res == null);
    }

    /**
     * This function will make sure that the randomly generated
     * hex strings aren't null or blank
     */
    @Test
    public void GenerateRandomHexNullOrBlankTest() {
        var res = main.Common.Security.GenerateRandomHex(20);
        assertFalse(res.isBlank() || res.isEmpty() || res == null);
    }

    /**
     * This function will make sure that the generated TOTP Secrets
     * aren't null or blank
     */
    @Test
    public void GenerateTOTPSecretNullOrBlankTest() {
        var res = main.Common.Security.GenerateTOTPSecret();
        assertFalse(res.isBlank() || res.isEmpty() || res == null);
    }
}