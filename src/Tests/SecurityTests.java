package tests;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SecurityTests {

    @Test
    public void HashStringNullOrBlankTest() {
        var res = Common.Security.HashString("BLANK");
        assertFalse(res.isBlank() || res.isEmpty() || res == null);
    }

    @Test
    public void GenerateRandomHexNullOrBlankTest() {
        var res = Common.Security.GenerateRandomHex(20);
        assertFalse(res.isBlank() || res.isEmpty() || res == null);
    }

    @Test
    public void GenerateTOTPSecretNullOrBlankTest() {
        var res = Common.Security.GenerateTOTPSecret();
        assertFalse(res.isBlank() || res.isEmpty() || res == null);
    }
}