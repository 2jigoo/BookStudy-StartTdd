package chap08.login.service;

import chap08.login.dto.LoginResult;
import chap08.login.entity.Customer;
import chap08.login.repository.CustomerRepository;
import chap08.login.utils.AuthUtil;

public class LoginService {

    private String authKey = "somekey";
    private CustomerRepository customerRepository;

    public LoginService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public LoginResult login(String id, String pw) {
        int response = 0;
        boolean authorized = AuthUtil.authorize(authKey);

        if (authorized) {
            response = AuthUtil.authenticate(id, pw);
        } else {
            response = -1;
        }

        if (response == -1) {
            return LoginResult.badAuthKey();
        }

        if (response == 1) {
            Customer customer = customerRepository.findOne(id);
            return LoginResult.authenticated(customer);
        } else {
            return LoginResult.fail(response);
        }
    }


}
