package com.omerfarukozcan.controller;

import com.omerfarukozcan.model.SignInRequest;
import com.omerfarukozcan.model.SignInResponse;
import com.omerfarukozcan.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class LoginController {

    private final UserService service;

    public LoginController(UserService service) {
        this.service = service;
    }

    @GetMapping("/admin/logout")
    public String logout() {

        service.logout();

        return "redirect:/admin/login";
    }

    @GetMapping("/admin/login")
    public String login() {
        return "login";
    }

    @PostMapping("/admin/sign-in")
    @ResponseBody
    public SignInResponse signIn(@ModelAttribute("signInForm") SignInRequest form) {
        return new SignInResponse(service.signIn(form));
    }

}
