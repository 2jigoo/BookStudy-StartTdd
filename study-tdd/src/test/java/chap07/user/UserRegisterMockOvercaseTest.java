package chap07.user;

import chap07.user.entity.User;
import chap07.user.repository.UserRepository;
import chap07.user.service.EmailNotifier;
import chap07.user.service.UserRegisterService;
import chap07.user.validator.WeakPasswordChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class UserRegisterMockOvercaseTest {

    private UserRegisterService userRegisterService;
    private WeakPasswordChecker mockPasswordChecker = Mockito.mock(WeakPasswordChecker.class);
    private UserRepository mockRepository = Mockito.mock(UserRepository.class);
    private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);


    @BeforeEach
    void setUp() {
        userRegisterService = new UserRegisterService(mockPasswordChecker, mockRepository, mockEmailNotifier);
    }

    @Test
    void duplicatedIdNotExistsRegisterSuccess() {
        userRegisterService.register("id", "pw", "email");

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        BDDMockito.then(mockRepository).should().save(captor.capture());

        User savedUser = captor.getValue();
        assertEquals("id", savedUser.getId());
        assertEquals("email", savedUser.getEmail());
    }

}
