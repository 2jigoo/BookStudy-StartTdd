package chap09.user;

import org.springframework.stereotype.Component;

@Component
public class SimpleWeakPasswordChecker implements WeakPasswordChecker {
    @Override
    public boolean checkPasswordWeak(String password) {
        return password == null || password.length() < 5;
    }

}
