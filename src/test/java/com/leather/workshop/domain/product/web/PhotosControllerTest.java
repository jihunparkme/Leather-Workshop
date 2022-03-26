package com.leather.workshop.domain.product.web;

import com.leather.workshop.domain.login.domain.User;
import com.leather.workshop.domain.login.domain.UserRepository;
import com.leather.workshop.global.config.security.dto.SessionUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        properties = {"spring.config.location=classpath:application.yml"}
)
@AutoConfigureMockMvc
class PhotosControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @Autowired
    UserRepository userRepository;

    MockHttpSession session;

    @Value("${file.directory}")
    private String fileDir;

    @BeforeEach
    void setUp() {
        User user = userRepository.findByEmail("ccc@naver.com").get();

        mockMvc = MockMvcBuilders
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
    void list() {
    }

    @Test
    void 무한_스크롤링() throws Exception {
        ResultActions perform = mockMvc.perform(get("/photos/list/card"));

        perform
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("성공적으로 조회되었습니다."))
                .andExpect(jsonPath("$.count").value("4"))
                .andExpect(jsonPath("$.totalElements").value("4"))
                .andExpect(jsonPath("$.totalPages").value("1"));
    }
}