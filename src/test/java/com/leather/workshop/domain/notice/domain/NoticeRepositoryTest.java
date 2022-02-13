package com.leather.workshop.domain.notice.domain;

import com.leather.workshop.domain.notice.exception.NoticeNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.List;

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

    @Test
    void 공지사항_등록_조회_성공() {
        //given
        Notice saveNotice = noticeRepository.save(Notice.builder()
                .userId(1L)
                .title(title)
                .contents(contents)
                .hits(0L)
                .build());

        //when
        Notice notice = noticeRepository.findById(saveNotice.getId())
                .orElseThrow(RuntimeException::new);

        //then
        assertThat(notice.getUserId()).isEqualTo(1L);
        assertThat(notice.getTitle()).isEqualTo(title);
        assertThat(notice.getContents()).isEqualTo(contents);
        assertThat(notice.getHits()).isEqualTo(0L);
    }

    @Test
    void 공지사항_조회_실패() {
        assertThatThrownBy(() ->
                noticeRepository.findById(10000L)
                        .orElseThrow(() -> new NoticeNotFoundException("해당 공지사항이 없습니다. id=" + 10000L)))
                .isInstanceOf(NoticeNotFoundException.class);
    }

    @Test
    void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2022, 01, 27, 0,0,0);
        noticeRepository.save(Notice.builder()
                .userId(1L)
                .title(title)
                .contents(contents)
                .hits(0L)
                .build());

        //when
        List<Notice> noticeList = noticeRepository.findAll();

        //then
        Notice notice = noticeList.get(0);

        System.out.println("CreatedDateTime = " + notice.getCreatedDateTime() + ", ModifiedDateTime" + notice.getModifiedDateTime());
        assertThat(notice.getCreatedDateTime()).isAfter(now);
        assertThat(notice.getModifiedDateTime()).isAfter(now);
    }
}