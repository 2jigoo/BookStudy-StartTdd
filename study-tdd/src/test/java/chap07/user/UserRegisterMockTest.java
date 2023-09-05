package chap07.user;

import chap07.debit.dto.AutoDebitReq;
import chap07.debit.entity.CardValidity;
import chap07.user.exception.WeakPasswordException;
import chap07.user.repository.MemoryUserRepository;
import chap07.user.service.EmailNotifier;
import chap07.user.service.UserRegisterService;
import chap07.user.validator.WeakPasswordChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

public class UserRegisterMockTest {

    private UserRegisterService userRegisterService;
    private WeakPasswordChecker mockPasswordChecker = Mockito.mock(WeakPasswordChecker.class);
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);


    @BeforeEach
    void setUp() {
        userRegisterService = new UserRegisterService(mockPasswordChecker, fakeRepository, mockEmailNotifier);
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    void weakPasswordThenFailedToRegister() {
        BDDMockito.given(mockPasswordChecker.checkPasswordWeak("pw"))
                .willReturn(true);

        assertThrows(WeakPasswordException.class, () -> {
            userRegisterService.register("id", "pw", "email");
        });
    }

    @DisplayName("회원 가입시 암호 검사 수행")
    @Test
    void checkPassword() {
        userRegisterService.register("id", "pw", "email");

        BDDMockito.then(mockPasswordChecker)
                .should()
                .checkPasswordWeak(BDDMockito.anyString());
    }

    @DisplayName("가입하면 메일 전송")
    @Test
    void whenRegisterThenSendMail() {
        userRegisterService.register("id", "pw", "email@email.com");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        BDDMockito.then(mockEmailNotifier)
                .should().sendRegisterEmail(captor.capture());

        String realEamil = captor.getValue();
        assertEquals("email@email.com", realEamil);
    }

}
