package chap07.join;

import chap07.join.idcheck.DupIdException;
import chap07.join.idcheck.User;
import chap07.join.idcheck.UserRepository;
import chap07.join.passwordcheck.WeakPasswordChecker;
import chap07.join.passwordcheck.WeakPasswordException;
import chap07.join.spy.EmailNotifier;

public class UserRegister {
    private WeakPasswordChecker passwordChecker;
    private UserRepository userRepository;
    private EmailNotifier emailNotifier;

    public UserRegister(WeakPasswordChecker passwordChecker, UserRepository userRepository,EmailNotifier emailNotifier){
        this.passwordChecker = passwordChecker;
        this.userRepository = userRepository;
        this.emailNotifier = emailNotifier;
    }
    public void register(String id, String pw, String email){
        if (passwordChecker.checkPasswordWeak(pw)){
            throw new WeakPasswordException();
        }
        User user = userRepository.findById(id);
        if (user != null) {
            throw new DupIdException();
        }
        userRepository.save(new User(id,pw,email));
        emailNotifier.sendRegisterEmail(email);
    }
}

