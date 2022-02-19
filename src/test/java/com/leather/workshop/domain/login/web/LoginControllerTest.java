package com.leather.workshop.domain.login.web;

import com.google.gson.JsonObject;
import com.leather.workshop.domain.login.domain.UserRepository;
import com.leather.workshop.domain.login.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    WebApplicationContext context;

    MockMvc mvc;

    @Autowired
    LoginService loginService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(roles = "USER")
    void 사용자_탈퇴_성공() throws Exception {
        String url = "http://localhost:" + port + "/mypage/withdraw";

        JsonObject data = new JsonObject();
        data.addProperty("email", "ccc@naver.com");

        //when
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(data)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void 사용자_탈퇴_실패() throws Exception {
        String url = "http://localhost:" + port + "/mypage/withdraw";

        JsonObject data = new JsonObject();
        data.addProperty("email", "abc@naver.com");

        //when
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(data)))
                .andExpect(status().isNotFound());
    }
}