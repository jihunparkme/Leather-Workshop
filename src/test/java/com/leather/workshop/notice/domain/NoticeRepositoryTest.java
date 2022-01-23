package com.leather.workshop.notice.domain;

import com.leather.workshop.exception.NoticeNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class NoticeRepositoryTest {

    @Autowired
    NoticeRepository noticeRepository;

    @AfterEach
    void cleanUp() {
        noticeRepository.deleteAll();
    }

    private String title = "제목";
    private String contents = "내용";
    private LocalDateTime now = LocalDateTime.now();

    @Test
    void 공지사항_조회_성공() {
        //given
        Notice saveNotice = noticeRepository.save(Notice.builder()
                .memberId(1L)
                .title(title)
                .contents(contents)
                .hits(0L)
                .createDateTime(now)
                .build());

        //when
        Notice notice = noticeRepository.findById(saveNotice.getId())
                .orElseThrow(RuntimeException::new);

        //then
        assertThat(notice.getMemberId()).isEqualTo(1L);
        assertThat(notice.getTitle()).isEqualTo(title);
        assertThat(notice.getContents()).isEqualTo(contents);
        assertThat(notice.getHits()).isEqualTo(0L);
        assertThat(notice.getCreateDateTime()).isEqualTo(now);
    }

    @Test
    void 공지사항_조회_실패() {
        assertThatThrownBy(() ->
                noticeRepository.findById(10000L)
                        .orElseThrow(() -> new NoticeNotFoundException("no notice id :" + 10000L)))
                .isInstanceOf(NoticeNotFoundException.class);
    }
}