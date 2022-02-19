package com.leather.workshop.domain.notice.web;

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

@SpringBootTest(
    properties = {"spring.config.location=classpath:application.yml"}
)
@AutoConfigureMockMvc
public class NoticeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    MockHttpSession session;

    User user = User.builder()
            .name("Aaron")
            .email("jihunpark.tech@gmail.com")
            .picture("")
            .role(Role.ADMIN)
            .build();

    @BeforeEach
    void setUp() {
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

    private String title = "제목";
    private String contents = "내용";

    @Test
    public void 공지사항_리스트() throws Exception {

        ResultActions perform = mockMvc.perform(get("/notice"));

        perform
                .andExpect(status().isOk())
                .andExpect(view().name("notice/notice-list"))
                .andExpect(model().attributeExists("noticeListPage"))
                .andExpect(model().attributeExists("page"));
    }

    @Test
    public void 공지사항_보기() throws Exception {
        ResultActions perform = mockMvc.perform(get("/notice/" + 1));

        perform
                .andExpect(status().isOk())
                .andExpect(view().name("notice/notice-view"))
                .andExpect(model().attributeExists("notice"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void 공지사항_등록_페이지_이동() throws Exception {
        ResultActions perform = mockMvc.perform(get("/notice/add")
                                        .session(session));

        perform
                .andExpect(status().isOk())
                .andExpect(view().name("notice/notice-add"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void 공지사항_등록_성공() throws Exception {

        ResultActions perform = mockMvc.perform(post("/notice/add")
                                .param("userId", "1")
                                .param("title", title)
                                .param("contents", contents));

        perform
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/notice/{id}"))
                .andExpect(redirectedUrl("/notice/13?status=true"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void 공지사항_등록_불가() throws Exception {

        ResultActions perform = mockMvc.perform(post("/notice/add")
                .session(session)
                .param("title", title)
                .param("contents", "")
                .param("session.user.name", "test"));

        perform
                .andExpect(status().isOk())
                .andExpect(view().name("notice/notice-add"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void 공지사항_수정_페이지_이동() throws Exception {

        ResultActions perform = mockMvc.perform(get("/notice/1/edit")
                                        .session(session));

        perform
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("notice"))
                .andExpect(view().name("notice/notice-edit"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void 공지사항_수정_성공() throws Exception {

        ResultActions perform = mockMvc.perform(post("/notice/2/edit")
                .param("title", title)
                .param("contents", contents));

        perform
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/notice/{id}"))
                .andExpect(redirectedUrl("/notice/2?status=true"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void 공지사항_수정_불가() throws Exception {

        ResultActions perform = mockMvc.perform(post("/notice/1/edit")
                .session(session)
                .param("title", title)
                .param("contents", ""));

        perform
                .andExpect(status().isOk())
                .andExpect(view().name("notice/notice-edit"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void 공지사항_삭제_성공() throws Exception {

        ResultActions perform = mockMvc.perform(post("/notice/1/delete"));

        perform
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/notice"))
                .andExpect(redirectedUrl("/notice"));
    }
}