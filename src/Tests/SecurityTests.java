package Tests;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SecurityTests {

    @Test
    public void HashStringNullOrBlankTest() {
        var res = main.Common.Security.HashString("BLANK");
        assertFalse(res.isBlank() || res.isEmpty() || res == null);
    }

    @Test
    public void GenerateRandomHexNullOrBlankTest() {
        var res = main.Common.Security.GenerateRandomHex(20);
        assertFalse(res.isBlank() || res.isEmpty() || res == null);
    }

    @Test
    public void GenerateTOTPSecretNullOrBlankTest() {
        var res = main.Common.Security.GenerateTOTPSecret();
        assertFalse(res.isBlank() || res.isEmpty() || res == null);
    }
}