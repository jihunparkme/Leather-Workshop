package com.leather.workshop.domain.Index.web;

import com.leather.workshop.global.config.security.LoginUser;
import com.leather.workshop.global.config.security.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("user", user);

        return "index";
    }
}
