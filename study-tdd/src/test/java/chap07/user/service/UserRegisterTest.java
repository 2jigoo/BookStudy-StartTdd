package chap07.user.service;

import chap07.user.entity.User;
import chap07.user.exception.DuplicatedIdException;
import chap07.user.exception.WeakPasswordException;
import chap07.user.repository.MemoryUserRepository;
import chap07.user.service.SpyEmailNotifier;
import chap07.user.service.UserRegisterService;
import chap07.user.validator.StubWeakPasswordChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRegisterTest {

    private UserRegisterService userRegisterService;
    private StubWeakPasswordChecker stubPasswordChecker = new StubWeakPasswordChecker();
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();

    private SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();


    @BeforeEach
    void setUp() {
        userRegisterService = new UserRegisterService(stubPasswordChecker, fakeRepository, spyEmailNotifier);
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    void weakPassword() {
        stubPasswordChecker.setWeak(true);

        assertThrows(WeakPasswordException.class, () -> {
            userRegisterService.register("id", "pw", "email");
        });
    }

    @DisplayName("이미 같은 ID가 존재하면 가입 실패")
    @Test
    void duplicatedIdExist() {
        fakeRepository.save(new User("id", "pw1", "email@email.com"));

        assertThrows(DuplicatedIdException.class, () -> {
            userRegisterService.register("id", "pw2", "email");
        });
    }

    @DisplayName("같은 ID가 없으면 가입 성공")
    @Test
    void duplicatedIdNotExistsRegisterSuccess() {
        userRegisterService.register("id", "pw", "email");

        User savedUser = fakeRepository.findById("id"); // 가입 결과 확인
        assertEquals("id", savedUser.getId());
        assertEquals("email", savedUser.getEmail());
    }

    @DisplayName("가입하면 메일을 전송함")
    @Test
    void whenRegisterThenSendMail() {
        userRegisterService.register("id", "pw", "email@email.com");

        assertTrue(spyEmailNotifier.isCalled());
        assertEquals("email@email.co                                                                                                                                              m", spyEmailNotifier.getEmail());
    }

}
