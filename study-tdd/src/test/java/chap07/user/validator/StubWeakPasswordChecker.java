package chap07.user.validator;

public class StubWeakPasswordChecker implements WeakPasswordChecker {

    private boolean weak;

    public void setWeak(boolean weak) {
        this.weak = weak;
    }

    @Override
    public boolean checkPasswordWeak(String password) {
        return weak;
    }
}
