package chap02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PasswordStrengthMeterTest {

    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    public void assertStrength(String password, PasswordStrength expectedStrength) {
        PasswordStrength actualStrength = meter.meter(password);
        assertEquals(expectedStrength, actualStrength);
    }

    @Test
    void meetsAllCriteriaThenStrong() {
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
        assertStrength("abc1!Add", PasswordStrength.STRONG);
    }

    @Test
    void meetsOtherCriteriaThenNormal() {
        assertStrength("ab12!@A", PasswordStrength.NORMAL);
        assertStrength("ab12!c", PasswordStrength.NORMAL);
    }

    @Test
    void meetsOtherCriteriaExceptForNumberThenNormal() {
        assertStrength("ab!@ABqwer", PasswordStrength.NORMAL);
    }

    @Test
    void nullInputThenInvalid() {
        assertStrength(null, PasswordStrength.INVALID);
    }

    @Test
    void meetsOtherCriteriaExceptForUppercaseThenNormal() {
        assertStrength("ab12!@df", PasswordStrength.NORMAL);
    }

    @Test
    void meetsOnlyLengthCriteriaThenWeak() {
        assertStrength("abcdefghi", PasswordStrength.WEAK);
    }

    @Test
    void meetsOnlyNumCriteriaThenWeak() {
        assertStrength("12345", PasswordStrength.WEAK);
    }

}
