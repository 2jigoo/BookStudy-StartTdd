package chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        if (s == null || s.isEmpty()) {
            return PasswordStrength.INVALID;
        }

        if (s.length() < 8) {
            return PasswordStrength.NORMAL;
        }

        boolean containsNumber = meetsContainingNumberCriteria(s);
        if (!containsNumber) {
            return PasswordStrength.NORMAL;
        }

        boolean containsUppercase = meetsContainingUppercaseCriteria(s);
        if (!containsUppercase) {
            return PasswordStrength.NORMAL;
        }

        return PasswordStrength.STRONG;
    }

    private boolean meetsContainingUppercaseCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }

    private boolean meetsContainingNumberCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }
}
