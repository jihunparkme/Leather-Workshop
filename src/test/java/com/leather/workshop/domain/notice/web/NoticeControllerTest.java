package com.leather.workshop.domain.notice.web;

import com.leather.workshop.domain.notice.domain.NoticeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(
    properties = {"spring.config.location=classpath:application-test.properties"}
)
@AutoConfigureMockMvc
public class NoticeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    NoticeRepository noticeRepository;

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
    public void 공지사항_등록_페이지_이동() throws Exception {
        ResultActions perform = mockMvc.perform(get("/notice/add"));

        perform
                .andExpect(status().isOk())
                .andExpect(view().name("notice/notice-add"));
    }

    @Test
    public void 공지사항_등록_성공() throws Exception {

        ResultActions perform = mockMvc.perform(post("/notice/add")
                                .param("title", title)
                                .param("contents", contents));

        perform
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/notice/{id}"))
                .andExpect(redirectedUrl("/notice/13?status=true"));
    }

    @Test
    public void 공지사항_등록_불가() throws Exception {

        ResultActions perform = mockMvc.perform(post("/notice/add")
                .param("title", title)
                .param("contents", ""));

        perform
                .andExpect(status().isOk())
                .andExpect(view().name("notice/notice-add"));
    }

    @Test
    public void 공지사항_수정_페이지_이동() throws Exception {

        ResultActions perform = mockMvc.perform(get("/notice/1/edit"));

        perform
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("notice"))
                .andExpect(view().name("notice/notice-edit"));
    }

    @Test
    public void 공지사항_수정_성공() throws Exception {

        ResultActions perform = mockMvc.perform(post("/notice/1/edit")
                .param("title", title)
                .param("contents", contents));

        perform
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/notice/{id}"))
                .andExpect(redirectedUrl("/notice/1?status=true"));
    }

    @Test
    public void 공지사항_수정_불가() throws Exception {

        ResultActions perform = mockMvc.perform(post("/notice/1/edit")
                .param("title", title)
                .param("contents", ""));

        perform
                .andExpect(status().isOk())
                .andExpect(view().name("notice/notice-edit"));
    }
}