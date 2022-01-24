package com.leather.workshop.notice.web;

import com.leather.workshop.notice.domain.Notice;
import com.leather.workshop.notice.domain.NoticeRepository;
import com.leather.workshop.notice.web.dto.NoticeSaveRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NoticeApiControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    NoticeRepository noticeRepository;

    @AfterEach
    void clear() {
        noticeRepository.deleteAll();
    }

    private String title = "제목";
    private String contents = "내용";
    private LocalDateTime now = LocalDateTime.now();

    @Test
    void 공지사항_등록_성공() {
        //gevin
        NoticeSaveRequest noticeSaveRequest = NoticeSaveRequest.builder()
                .memberId(1L)
                .title(title)
                .contents(contents)
                .hits(0L)
                .createDateTime(now)
                .build();

        String url = "http://localhost:" + port + "/notice";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, noticeSaveRequest, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Notice> all = noticeRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContents()).isEqualTo(contents);

    }
}