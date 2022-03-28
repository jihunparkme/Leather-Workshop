package com.leather.workshop.domain.notice.service;

import com.leather.workshop.domain.notice.domain.Notice;
import com.leather.workshop.domain.notice.domain.NoticeRepository;
import com.leather.workshop.domain.notice.web.dto.NoticeDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class NoticeServiceTest {

    @Mock
    private NoticeRepository noticeRepository;

    @InjectMocks
    private NoticeService noticeService;

    Long id = 10L;
    Long userId = 1L;
    String title = "제목";
    String contents = "내용";
    Long hits = 10L;

    String updateTitle = "제목 수정";
    String updateContents = "내용 수정";

    private Notice notice = Notice.builder()
            .id(id)
            .userId(userId)
            .title(title)
            .contents(contents)
            .hits(hits)
            .modifiedDateTime(null).build();

    @Test
    @DisplayName("공지사항 등록 성공")
    void save() {

        NoticeDto.SaveRequest request = NoticeDto.SaveRequest.builder()
                .userId(userId)
                .title(title)
                .contents(contents)
                .hits(hits).build();

        lenient().when(noticeRepository.save((any()))).thenReturn(Optional.of(notice).get());

        noticeService.save(request);

        verify(noticeRepository).save(any());
    }

    @Test
    @DisplayName("공지사항 수정 성공")
    void update() {

        NoticeDto.UpdateRequest updateRequest = NoticeDto.UpdateRequest.builder()
                .id(id)
                .title(updateTitle)
                .contents(updateContents)
                .build();

        lenient().when(noticeRepository.findById(any())).thenReturn(Optional.of(notice));

        noticeService.update(id, updateRequest);

        verify(noticeRepository).findById(any());
    }

    @Test
    @DisplayName("공지사항 삭제 성공")
    void delete() {
        lenient().when(noticeRepository.findById(any())).thenReturn(Optional.of(notice));

        noticeService.delete(id);

        verify(noticeRepository).findById(any());
        verify(noticeRepository).delete(any());
    }

    @Test
    @DisplayName("공지사항 조회 성공")
    void findById() {
        lenient().when(noticeRepository.findById(any())).thenReturn(Optional.of(notice));

        NoticeDto.Response response = noticeService.findById(id);

        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getTitle()).isEqualTo(title);
        assertThat(response.getContents()).isEqualTo(contents);
        assertThat(response.getUserId()).isEqualTo(userId);
        assertThat(response.getHits()).isEqualTo(hits);

        verify(noticeRepository).findById(any());
    }

    @Test
    @DisplayName("공지사항 내림차순 조회 성공")
    void findAllDesc() {
        given(noticeRepository.findAllDesc()).willReturn(new ArrayList<>(Arrays.asList(notice)));

        noticeService.findAllDesc();

        verify(noticeRepository).findAllDesc();
    }
}