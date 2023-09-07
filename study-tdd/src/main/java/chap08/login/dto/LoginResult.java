package chap08.login.dto;

import chap08.login.entity.Customer;

public class LoginResult {

    public static LoginResult badAuthKey() {
        return null;
    }

    public static LoginResult authenticated(Customer customer) {
        return null;
    }

    public static LoginResult fail(int response) {
        return null;
    }

}
