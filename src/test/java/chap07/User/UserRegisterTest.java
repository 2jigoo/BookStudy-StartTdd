package chap07.User;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRegisterTest {
    private UserRegister userRegister;
    private StubWeakPasswordChecker stubPasswordChecker = new StubWeakPasswordChecker();

    @BeforeEach
    void setUp(){
        userRegister = new UserRegister(stubPasswordChecker);
    }
    @DisplayName("약한 암호면 가입 실패")
    @Test
    void WeakPassword(){
        stubPasswordChecker.setWeak(true); //암호가 약하다고 응답하도록 설정

        assertThrows(WeakPasswordException.class, ()->{
            userRegister.register("id","pw","email");
        });
    }


}