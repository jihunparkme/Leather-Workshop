package com.leather.workshop.domain.login.web;

import com.leather.workshop.domain.login.domain.User;
import com.leather.workshop.domain.login.service.LoginService;
import com.leather.workshop.global.common.response.BasicResponse;
import com.leather.workshop.global.config.security.LoginUser;
import com.leather.workshop.global.config.security.dto.SessionUser;
import com.leather.workshop.global.config.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login/user")
    public String login(HttpServletRequest request) {
        request.getSession().setAttribute(SessionConst.LOGIN_PREV_PAGE, request.getHeader("Referer"));
        return "login/login";
    }

    @GetMapping("/mypage")
    public String myPage(@LoginUser SessionUser user, Model model) {
        model.addAttribute("user", user);
        return "login/mypage";
    }

    @PostMapping(value = "/mypage/withdraw", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BasicResponse> withdraw(@LoginUser SessionUser user,
                                                  @RequestBody HashMap<String, Object> map,
                                                  Model model) {

        BasicResponse basicResponse = new BasicResponse();
        String email = (String) map.get("email");
        Optional<User> optUser = loginService.getUserRepository().findByEmail(email);
        if (optUser.isPresent()) {
            basicResponse = BasicResponse.builder()
                                        .code(HttpStatus.OK.value())
                                        .httpStatus(HttpStatus.OK)
                                        .message("성공적으로 탈퇴되었습니다.")
                                        .result(Collections.emptyList())
                                        .count(1)
                                        .build();

            loginService.getUserRepository().delete(optUser.get());
        } else {
            basicResponse = BasicResponse.builder()
                                        .code(HttpStatus.NOT_FOUND.value())
                                        .httpStatus(HttpStatus.NOT_FOUND)
                                        .message("사용자 정보를 찾을 수 없습니다. email=" + email)
                                        .result(Collections.emptyList())
                                        .count(0)
                                        .build();
        }

        return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
    }
}
