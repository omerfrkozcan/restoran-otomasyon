package com.omerfarukozcan.service;

import com.omerfarukozcan.controller.RequestUtil;
import com.omerfarukozcan.entity.UserModel;
import com.omerfarukozcan.model.SignInRequest;
import com.omerfarukozcan.repository.UserRepository;
import org.springframework.stereotype.Service;

import static com.omerfarukozcan.util.CommonUtil.encodePass;

@Service
public class UserService {

    private final UserRepository repository;
    private final RequestUtil requestUtil;

    public UserService(UserRepository repository, RequestUtil requestUtil) {
        this.repository = repository;
        this.requestUtil = requestUtil;
    }


    public void logout() {
        requestUtil.logout();
    }

    public boolean signIn(SignInRequest form) {
        final String email = form.getEmail();
        final String password = form.getPassword();

        final UserModel user = repository.findByEmail(email);

        final boolean success = user.getPassword().equals(encodePass(password));
        if (success) {
            requestUtil.setLoggedIn(true);
        }

        return success;
    }

}
