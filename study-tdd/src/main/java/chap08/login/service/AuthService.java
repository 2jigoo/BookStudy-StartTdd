package chap08.login.service;

import chap08.login.utils.AuthUtil;

public class AuthService {

    private String authKey = "somekey";

    public int ahthenticate(String id, String password) {
        boolean authorized = AuthUtil.authorize(authKey);
        if (!authorized) {
            return -1;
        }

        return AuthUtil.authenticate(id, password);
    }
}
