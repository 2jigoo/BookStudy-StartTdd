package chap07.user.service;

import chap07.user.validator.WeakPasswordChecker;
import chap07.user.exception.WeakPasswordException;

public class UserRegisterService {

    private WeakPasswordChecker passwordChecker;

    public UserRegisterService(WeakPasswordChecker passwordChecker) {
        this.passwordChecker = passwordChecker;
    }

    public void register(String id, String pw, String email) {
        if (passwordChecker.checkPasswordWeak(pw)) {
            throw new WeakPasswordException();
        }
    }

}
