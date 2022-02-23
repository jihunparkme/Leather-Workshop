package com.leather.workshop.domain.review.web;

import com.leather.workshop.domain.login.domain.Role;
import com.leather.workshop.domain.login.domain.User;
import com.leather.workshop.global.config.security.dto.SessionUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ReviewControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    WebApplicationContext context;

    MockHttpSession admin_session;
    MockHttpSession user_session;

    User admin = User.builder()
            .name("Aaron")
            .email("jihunpark.tech@gmail.com")
            .picture("")
            .role(Role.ADMIN)
            .build();

    User user = User.builder()
            .name("user")
            .email("user@gmail.com")
            .picture("")
            .role(Role.USER)
            .build();

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        admin_session = new MockHttpSession();
        admin_session.setAttribute("user", new SessionUser(admin));

        user_session = new MockHttpSession();
        user_session.setAttribute("user", new SessionUser(user));
    }

    @AfterEach
    void clean() {
        admin_session.clearAttributes();
        user_session.clearAttributes();
    }

    @Test
    void 리뷰_리스트() throws Exception {
        mvc.perform(get("/review")
                .session(user_session))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("reviewListPage"))
                .andExpect(model().attributeExists("page"));
    }
}