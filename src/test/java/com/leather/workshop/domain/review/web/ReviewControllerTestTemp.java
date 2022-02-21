package com.leather.workshop.domain.review.web;

import com.leather.workshop.domain.login.domain.Role;
import com.leather.workshop.domain.login.domain.User;
import com.leather.workshop.domain.review.domain.ReviewRepository;
import com.leather.workshop.domain.review.service.ReviewService;
import com.leather.workshop.global.config.security.SecurityConfig;
import com.leather.workshop.global.config.security.dto.SessionUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@WebMvcTest(controllers = ReviewController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        }
)
class ReviewControllerTestTemp {

    @Autowired
    private MockMvc mvc;

    @Autowired
    WebApplicationContext context;

    @MockBean
    ReviewService reviewService;

    @MockBean
    ReviewRepository reviewRepository;

    MockHttpSession session;

    User user = User.builder()
            .name("Aaron")
            .email("jihunpark.tech@gmail.com")
            .picture("")
            .role(Role.ADMIN)
            .build();

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        session = new MockHttpSession();
        session.setAttribute("user", new SessionUser(user));
    }

    @AfterEach
    void clean() {
        session.clearAttributes();
    }

    @Test
    void 리뷰_리스트() throws Exception {
    }
}