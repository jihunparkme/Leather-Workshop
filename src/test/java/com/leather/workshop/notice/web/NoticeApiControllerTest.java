package com.leather.workshop.notice.web;

import com.leather.workshop.notice.domain.Notice;
import com.leather.workshop.notice.domain.NoticeRepository;
import com.leather.workshop.notice.web.dto.NoticeSaveRequest;
import com.leather.workshop.notice.web.dto.NoticeUpdateRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.time.LocalDateTime;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

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

    @Test
    void 공지사항_등록_실패_제목_누락() {
        //gevin
        NoticeSaveRequest noticeSaveRequest = NoticeSaveRequest.builder()
                .memberId(1L)
                .contents(contents)
                .hits(0L)
                .build();

        String url = "http://localhost:" + port + "/notice";

        //when
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, noticeSaveRequest, String.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void 공지사항_수정_성공() {
        //given
        Notice saveNotice = noticeRepository.save(Notice.builder()
                .memberId(1L)
                .title(title)
                .contents(contents)
                .hits(0L)
                .build());

        Long updateId = saveNotice.getId();
        String expectedTitle = "제목222";
        String expectedContents = "내용222";

        NoticeUpdateRequest requestUpdateNotice = NoticeUpdateRequest.builder()
                .title(expectedTitle)
                .contents(expectedContents)
                .build();

        //when
        String url = "http://localhost:" + port + "/notice/" + updateId;
        HttpEntity<NoticeUpdateRequest> requestEntity = new HttpEntity<>(requestUpdateNotice);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Notice> all = noticeRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContents()).isEqualTo(expectedContents);
    }

    @Test
    void 공지사항_수정_실패() {

    }

    @Test
    void 공지사항_삭제_성공() {

    }

    @Test
    void 공지사항_삭제_실패() {

    }

    @Test
    void 공지사항_조회_성공() {
    }

    @Test
    void 공지사항_조회_실패() {

    }

    @Test
    void 전체_공지사항_조회_성공() {

    }
}