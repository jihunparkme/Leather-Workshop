package com.leather.workshop.domain.login.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login/user")
    public String login() {
        return "login/login";
    }
}
