package com.leather.workshop.domain.login.web;

import com.leather.workshop.global.config.session.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping("/login/user")
    public String login(HttpServletRequest request) {
        request.getSession().setAttribute(SessionConst.LOGIN_PREV_PAGE, request.getHeader("Referer"));
        return "login/login";
    }
}
