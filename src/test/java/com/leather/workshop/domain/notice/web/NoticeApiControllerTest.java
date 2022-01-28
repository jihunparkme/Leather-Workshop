package com.leather.workshop.domain.notice.web;

import com.leather.workshop.domain.notice.exception.NoticeNotFoundException;
import com.leather.workshop.domain.notice.domain.Notice;
import com.leather.workshop.domain.notice.domain.NoticeRepository;
import com.leather.workshop.domain.notice.web.dto.request.NoticeSaveRequest;
import com.leather.workshop.domain.notice.web.dto.request.NoticeUpdateRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    void 공지사항_삭제_성공() {
        //gevin
        Notice save = noticeRepository.save(Notice.builder()
                .memberId(1L)
                .title(title)
                .contents(contents)
                .hits(0L)
                .build());

        //when
        String url = "http://localhost:" + port + "/notice/" + save.getId();
        restTemplate.delete(url);

        //then
        assertThatThrownBy(() ->
                noticeRepository.findById(10000L)
                        .orElseThrow(() -> new NoticeNotFoundException("해당 공지사항이 없습니다. id=" + 10000L)))
                .isInstanceOf(NoticeNotFoundException.class)
                .hasMessageContaining("해당 공지사항이 없습니다.");
    }

    @Test
    void 공지사항_조회_성공() {
        //gevin
        Notice save = noticeRepository.save(Notice.builder()
                .memberId(1L)
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
        assertThat(responseEntity.getBody().getMemberId()).isEqualTo(save.getMemberId());
        assertThat(responseEntity.getBody().getTitle()).isEqualTo(save.getTitle());
        assertThat(responseEntity.getBody().getContents()).isEqualTo(save.getContents());
        assertThat(responseEntity.getBody().getHits()).isEqualTo(save.getHits());
    }

    @Test
    void 공지사항_조회_실패() {
        //when
        String url = "http://localhost:" + port + "/notice/" + 1000;
        ResponseEntity<Notice> responseEntity = restTemplate.getForEntity(url, Notice.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void 전체_공지사항_조회_성공() {
        for (int i = 0; i < 5; i++) {
            Notice save = noticeRepository.save(Notice.builder()
                    .memberId(1L)
                    .title(title)
                    .contents(contents)
                    .hits(0L)
                    .build());
        }

        List<Notice> all = noticeRepository.findAll();
        assertThat(all.size()).isEqualTo(5);
    }
}