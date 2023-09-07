package chap07.user.service;

import chap07.user.entity.User;
import chap07.user.exception.DuplicatedIdException;
import chap07.user.repository.UserRepository;
import chap07.user.validator.WeakPasswordChecker;
import chap07.user.exception.WeakPasswordException;

public class UserRegisterService {

    private WeakPasswordChecker passwordChecker;
    private UserRepository userRepository;

    private EmailNotifier emailNotifier;

    public UserRegisterService(WeakPasswordChecker passwordChecker, UserRepository userRepository, EmailNotifier emailNotifier) {
        this.passwordChecker = passwordChecker;
        this.userRepository = userRepository;
        this.emailNotifier = emailNotifier;
    }

    public void register(String id, String pw, String email) {
        if (passwordChecker.checkPasswordWeak(pw)) {
            throw new WeakPasswordException();
        }
        User user = userRepository.findById(id);
        if (user != null) {
            throw new DuplicatedIdException();

        }

        userRepository.save(new User(id, pw, email));

        emailNotifier.sendRegisterEmail(email);
    }

}
