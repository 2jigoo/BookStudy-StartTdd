package chap07.user;

import chap07.user.exception.WeakPasswordException;
import chap07.user.service.UserRegisterService;
import chap07.user.validator.StubWeakPasswordChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserRegisterTest {

    private UserRegisterService userRegisterService;
    private StubWeakPasswordChecker stubPasswordChecker = new StubWeakPasswordChecker();

    @BeforeEach
    void setUp() {
        userRegisterService = new UserRegisterService(stubPasswordChecker);
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    void weakPassword() {
        stubPasswordChecker.setWeak(true);

        assertThrows(WeakPasswordException.class, () -> {
            userRegisterService.register("id", "pw", "email");
        });
    }

}
