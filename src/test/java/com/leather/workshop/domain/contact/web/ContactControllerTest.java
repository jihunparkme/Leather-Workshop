package com.leather.workshop.domain.contact.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(
        properties = {"spring.config.location=classpath:application.yml"}
)
@AutoConfigureMockMvc
class ContactControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("문의 페이지 이동")
    void moveContactPage() throws Exception {
        ResultActions perform = mockMvc.perform(get("/contact"));

        perform
                .andExpect(status().isOk())
                .andExpect(view().name("contact/contact"));
    }

    @Test
    @DisplayName("문의 등록")
    void addContact() throws Exception {
        ResultActions perform = mockMvc.perform(post("/contact/add")
                                        .param("name", "이름")
                                        .param("phoneNumber", "010-1234-1234")
                                        .param("title", "테스트 제목")
                                        .param("contents", "테스트 내용"));


        perform
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("문의가 등록되었습니다."))
                .andExpect(jsonPath("$.count").value("1"));
    }
}