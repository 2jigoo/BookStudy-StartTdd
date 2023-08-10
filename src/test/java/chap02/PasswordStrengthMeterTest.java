package chap02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordStrengthMeterTest {
    private PasswordStrengthMeter passwordStrengthMeter = new PasswordStrengthMeter();
    private void assertStrength(String password, PasswordStrength expStr){
        PasswordStrength result = passwordStrengthMeter.meter(password);
        assertEquals(expStr,result);
    }
    @Test //모든 규칙을 충족하는 경우
    void meetsAllCriteria_Then_Strong(){
        assertStrength("ab12!@AB",PasswordStrength.STRONG);
        assertStrength("abc!Add",PasswordStrength.STRONG);
    }
    @Test //길이만 8글자 미만이고 나머지 조건은 충족하는 경우
    void meetsOtherCriteria_except_for_Length_Then_Normal(){
        assertStrength("ab12!@A",PasswordStrength.STRONG);
        assertStrength("Ab12!c",PasswordStrength.STRONG);
    }
    @Test //숫자를 포함하지 않고 나머지 조건은 충족하는 경우
    void meetsOtherCriteria_except_for_number_Then_Normal(){
        assertStrength("ab!@ABqwer",PasswordStrength.STRONG);
    }

    @Test //값이 없는 경우
    void nullInput_Then_Invalid(){
        assertStrength(null, PasswordStrength.INVALID);
        assertStrength("", PasswordStrength.INVALID);
    }

    @Test //대문자를 포함하지 않고 나머지 조건을 충족하는 경우
    void meetsOtherCriteria_except_for_Uppercase_Then_Normal(){
        assertStrength("ab12!@df", PasswordStrength.NORMAL);
    }

    @Test //길이가 8글자 이상인 조건만 충족하는 경우를 검증하기 위한 테스트 코드
    void meetsOnlyLengthCriteria_Then_Weak(){
        assertStrength("abdefghi",PasswordStrength.WEAK);
    }

    @Test //숫자 포함 조건만 충족하는 경우를 거증하기 위한 테스트
    void meetsOnlyNumCriteria_Then_Weak(){
        assertStrength("12345",PasswordStrength.WEAK);
    }

    @Test //대문자 포함 조건만 충족하는 경우를 검증하기 위한 테스트
    void meetsOnlyUpperCriteria_Then_Weak(){
        assertStrength("ABZEF", PasswordStrength.WEAK);
    }

    @Test //아무 조건도 충족하지 않은 경우를 검중하는 테스트 코드
    void meetsNoCriteria_Then_Weak(){
        assertStrength("abc", PasswordStrength.WEAK);
    }
}