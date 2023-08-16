package chap02;

public class PasswordStrengthMeter {
    //충족조건 수에 따라 암호 강도를 계산하는 로직
    public PasswordStrength meter(String s){
        if(s == null || s.isEmpty()) return PasswordStrength.INVALID;
        int metCounts = getMetCriteriaCounts(s);
        if(metCounts <= 1)return PasswordStrength.WEAK;
        if (metCounts == 2) return PasswordStrength.NORMAL;
        return PasswordStrength.STRONG;
    }
    private boolean meetsContainingNumberCriteria(String s){
        for(char ch : s.toCharArray()) {
            if (ch>= '0' && ch <='9'){
                return true;
            }
        }
        return false;
    }

    private boolean meetsContainingUppercaseCriteria(String s){
        for (char ch : s.toCharArray()){
            if (Character.isUpperCase(ch)){
                return true;
            }
        }
        return false;
    }
    //충족된 규칙 수를 카운트하는 로직
    private int getMetCriteriaCounts(String s){
        int metCounts = 0;
        if (s.length() >= 8)metCounts++;
        if(meetsContainingNumberCriteria(s))metCounts++;
        if(meetsContainingUppercaseCriteria(s))metCounts++;
        return metCounts;
    }
}
