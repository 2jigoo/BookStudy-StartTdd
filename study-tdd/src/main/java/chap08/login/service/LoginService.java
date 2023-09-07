package chap08.login.service;

import chap08.login.dto.LoginResult;
import chap08.login.entity.Customer;
import chap08.login.repository.CustomerRepository;
import chap08.login.utils.AuthUtil;

public class LoginService {

    private String authKey = "somekey";
    private CustomerRepository customerRepository;

    private AuthService authService = new AuthService();

    public LoginService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }


    public LoginResult login(String id, String pw) {
        int response = authService.ahthenticate(id, pw);

        if (response == -1) {
            return LoginResult.badAuthKey();
        }

        if (response == 1) {
            Customer customer = customerRepository.findOne(id);
            return LoginResult.authenticated(customer);
        }

        return LoginResult.fail(response);
    }


}
