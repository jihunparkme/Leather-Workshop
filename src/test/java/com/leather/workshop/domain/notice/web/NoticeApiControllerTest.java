package com.leather.workshop.domain.notice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leather.workshop.domain.notice.domain.Notice;
import com.leather.workshop.domain.notice.domain.NoticeRepository;
import com.leather.workshop.domain.notice.web.dto.NoticeDto;
import com.leather.workshop.global.common.exception.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NoticeApiControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    WebApplicationContext context;

    MockMvc mvc;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    NoticeRepository noticeRepository;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @AfterEach
    void clear() {
        noticeRepository.deleteAll();
    }

    private String title = "??????";
    private String contents = "??????";

    @Test
    @WithMockUser(roles = "ADMIN")
    void ????????????_??????_??????() throws Exception {
        //gevin
        NoticeDto.SaveRequest noticeSaveRequest = NoticeDto.SaveRequest.builder()
                .userId(1L)
                .title(title)
                .contents(contents)
                .hits(0L)
                .build();

        String url = "http://localhost:" + port + "/notice";

        //when
        mvc.perform(post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(noticeSaveRequest)))
                    .andExpect(status().isOk());

        //then
        List<Notice> all = noticeRepository.findAll();
        assertThat(all.get(all.size()-1).getTitle()).isEqualTo(title);
        assertThat(all.get(all.size()-1).getContents()).isEqualTo(contents);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void ????????????_??????_??????() throws Exception {
        //given
        Notice saveNotice = noticeRepository.save(Notice.builder()
                .userId(1L)
                .title(title)
                .contents(contents)
                .hits(0L)
                .build());

        Long updateId = saveNotice.getId();
        String expectedTitle = "??????222";
        String expectedContents = "??????222";

        NoticeDto.UpdateRequest requestUpdateNotice = NoticeDto.UpdateRequest.builder()
                .title(expectedTitle)
                .contents(expectedContents)
                .build();

        //when
        String url = "http://localhost:" + port + "/notice/" + updateId;
        HttpEntity<NoticeDto.UpdateRequest> requestEntity = new HttpEntity<>(requestUpdateNotice);

        mvc.perform(put(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestUpdateNotice)))
                .andExpect(status().isOk());

        //then
        Notice notice = noticeRepository.findById(updateId).get();
        assertThat(notice.getTitle()).isEqualTo(expectedTitle);
        assertThat(notice.getContents()).isEqualTo(expectedContents);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void ????????????_??????_??????() throws Exception {
        //gevin
        Notice save = noticeRepository.save(Notice.builder()
                .userId(1L)
                .title(title)
                .contents(contents)
                .hits(0L)
                .build());

        //when
        String url = "http://localhost:" + port + "/notice/" + save.getId();
        mvc.perform(delete(url)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //then
        assertThatThrownBy(() ->
                noticeRepository.findById(save.getId())
                        .orElseThrow(() -> new EntityNotFoundException("?????? ??????????????? ????????????. id=" + 10000L)))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("?????? ??????????????? ????????????.");
    }

    @Test
    void ????????????_??????_??????() {
        //gevin
        Notice save = noticeRepository.save(Notice.builder()
                .userId(1L)
                .title(title)
                .contents(contents)
                .hits(0L)
                .build());

        //when
        String url = "http://localhost:" + port + "/notice/" + save.getId();
        ResponseEntity<Notice> responseEntity = restTemplate.getForEntity(url, Notice.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getId()).isEqualTo(save.getId());
        assertThat(responseEntity.getBody().getUserId()).isEqualTo(save.getUserId());
        assertThat(responseEntity.getBody().getTitle()).isEqualTo(save.getTitle());
        assertThat(responseEntity.getBody().getContents()).isEqualTo(save.getContents());
        assertThat(responseEntity.getBody().getHits()).isEqualTo(save.getHits());
    }

    @Test
    void ????????????_??????_??????() {
        //when
        String url = "http://localhost:" + port + "/notice/" + 1000;
        ResponseEntity<Notice> responseEntity = restTemplate.getForEntity(url, Notice.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void ??????_????????????_??????_??????() {
        for (int i = 0; i < 5; i++) {
            Notice save = noticeRepository.save(Notice.builder()
                    .userId(1L)
                    .title(title)
                    .contents(contents)
                    .hits(0L)
                    .build());
        }

        List<Notice> all = noticeRepository.findAll();
        assertThat(all.size()).isEqualTo(5);
    }
}