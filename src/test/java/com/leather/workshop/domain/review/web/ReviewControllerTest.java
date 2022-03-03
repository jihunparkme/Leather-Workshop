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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
            .name("JiHun")
            .email("bbb@naver.com")
            .picture("")
            .role(Role.USER)
            .build();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
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
        mockMvc.perform(get("/review")
                .session(user_session))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("reviewListPage"))
                .andExpect(model().attributeExists("page"));
    }

    @Test
    @WithMockUser(roles={"USER","ADMIN"})
    void 리뷰_추가_페이지_이동() throws Exception {
        ResultActions perform = mockMvc.perform(get("/review/add")
                .session(user_session));

        perform
                .andExpect(status().isOk())
                .andExpect(view().name("review/review-add"));
    }

    @Test
    void 리뷰_추가_페이지_이동_권한_부족() throws Exception {
        ResultActions perform = mockMvc.perform(get("/review/add")
                .session(user_session));

        perform
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login/user"));
    }

    @Test
    @WithMockUser(roles={"USER","ADMIN"})
    void 리뷰_추가() throws Exception {
        ResultActions perform = mockMvc.perform(post("/review/add")
                .param("userId", "3")
                .param("nickname", "Park")
                .param("contents", "내용"));

        perform
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/review"))
                .andExpect(redirectedUrl("/review"));
    }

    @Test
    void 리뷰_추가_권한_부족() throws Exception {
        ResultActions perform = mockMvc.perform(post("/review/add")
                .param("userId", "3")
                .param("nickname", "Park")
                .param("contents", "내용"));

        perform
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login/user"));
    }

    @Test
    @WithMockUser(roles={"USER","ADMIN"})
    void 리뷰_수정_페이지_이동() throws Exception {
        ResultActions perform = mockMvc.perform(get("/review/1/edit")
                .session(admin_session));

        perform
                .andExpect(status().isOk())
                .andExpect(view().name("/review/review-edit"));
    }

    @Test
    void 리뷰_수정_페이지_이동_권한_부족() throws Exception {
        ResultActions perform = mockMvc.perform(get("/review/3/edit")
                .session(user_session));

        perform
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles={"USER","ADMIN"})
    void 리뷰_삭제() throws Exception {
        ResultActions perform = mockMvc.perform(delete("/review/3")
                .session(admin_session));

        perform
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("후기가 삭제되었습니다."));
    }

    @Test
    void 리뷰_삭제_권한_부족() throws Exception {
        ResultActions perform = mockMvc.perform(delete("/review/3")
                .session(admin_session));

        perform
                .andExpect(status().is3xxRedirection());
    }

}