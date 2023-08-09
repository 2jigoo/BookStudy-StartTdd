package chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        if (s == null || s.isEmpty()) {
            return PasswordStrength.INVALID;
        }

        boolean lengthEnough = s.length() >= 8;
        boolean containsNumber = meetsContainingNumberCriteria(s);
        boolean containsUppercase = meetsContainingUppercaseCriteria(s);

        if (lengthEnough && !containsNumber && !containsUppercase)
            return PasswordStrength.WEAK;

        if (!lengthEnough && containsNumber && !containsUppercase)
            return PasswordStrength.WEAK;

        if (!lengthEnough && !containsNumber && containsUppercase)
            return PasswordStrength.WEAK;

        if (!lengthEnough) return PasswordStrength.NORMAL;
        if (!containsNumber) return PasswordStrength.NORMAL;
        if (!containsUppercase) return PasswordStrength.NORMAL;

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
