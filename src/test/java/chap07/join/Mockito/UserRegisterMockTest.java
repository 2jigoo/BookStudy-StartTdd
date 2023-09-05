package chap07.join.Mockito;


import chap07.join.UserRegister;
import chap07.join.idcheck.MemoryUserRepository;
import chap07.join.passwordcheck.WeakPasswordChecker;
import chap07.join.passwordcheck.WeakPasswordException;
import chap07.join.spy.EmailNotifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserRegisterMockTest {
    private UserRegister userRegister;
    private WeakPasswordChecker mockPasswordChecker = Mockito.mock(WeakPasswordChecker.class);
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);

    @BeforeEach
    void setUp(){
        userRegister = new UserRegister(mockPasswordChecker,fakeRepository,mockEmailNotifier);
    }
    @DisplayName("약한 암호면 가입 실패")
    @Test
    void weakPassword(){
        BDDMockito.given(mockPasswordChecker.checkPasswordWeak("pw"))
                .willReturn(true);
        assertThrows(WeakPasswordException.class, ()-> {
            userRegister.register("id","pw","email");
        });
    }
    @DisplayName("회원 가입시 암호 검사 수행함")
    @Test
    void checkPassword(){
        userRegister.register("id","pw","email");

        BDDMockito.then(mockPasswordChecker)
                .should()
                .checkPasswordWeak(BDDMockito.anyString());
    }

    @DisplayName("가입하면 메일을 전송함")
    @Test
    void whenRegisterThenSendMail(){
        userRegister.register("id","pw","email");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        BDDMockito.then(mockEmailNotifier)
                .should().sendRegisterEmail(captor.capture());

        String realEmail = captor.getValue();
        assertEquals("email@email.com", realEmail);
    }

}