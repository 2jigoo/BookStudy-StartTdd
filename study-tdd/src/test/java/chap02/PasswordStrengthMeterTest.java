package chap02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {

    @Test
    void meetsAllCriteriaThenStrong() {
        PasswordStrengthMeter meter = new PasswordStrengthMeter();

        PasswordStrength result = meter.meter("ab12!@AB");
        assertEquals(PasswordStrength.STRONG, result);

        PasswordStrength result2 = meter.meter("abc1!Add");
        assertEquals(PasswordStrength.STRONG, result2);
    }

    @Test
    void meetsOtherCriteriaThenNormal() {
        PasswordStrengthMeter meter = new PasswordStrengthMeter();

        PasswordStrength result = meter.meter("ab12!@A");
        assertEquals(PasswordStrength.NORMAL, result);

        PasswordStrength result2 = meter.meter("ab12!c");
        assertEquals(PasswordStrength.NORMAL, result2);
    }

}
