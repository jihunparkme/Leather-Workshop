package com.leather.workshop.notice.service;

import com.leather.workshop.exception.NoticeNotFoundException;
import com.leather.workshop.notice.domain.Notice;
import com.leather.workshop.notice.domain.NoticeRepository;
import com.leather.workshop.notice.web.dto.NoticeListResponse;
import com.leather.workshop.notice.web.dto.NoticeResponse;
import com.leather.workshop.notice.web.dto.NoticeSaveRequest;
import com.leather.workshop.notice.web.dto.NoticeUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    public Long save(NoticeSaveRequest noticeSaveRequestDto) {
        return noticeRepository.save(noticeSaveRequestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, NoticeUpdateRequest noticeUpdateRequest) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new NoticeNotFoundException("해당 공지사항이 없습니다. id=" + id));

        notice.update(noticeUpdateRequest.getTitle(), noticeUpdateRequest.getContents());

        return id;
    }

    @Transactional
    public void delete(Long id) {
        Notice posts = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 공지사항이 없습니다. id=" + id));

        noticeRepository.delete(posts);
    }

    @Transactional(readOnly = true)
    public NoticeResponse findById(Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new NoticeNotFoundException("해당 공지사항이 없습니다. id=" + id));

        return new NoticeResponse(notice);
    }

    @Transactional(readOnly = true)
    public List<NoticeListResponse> findAllDesc() {
        return noticeRepository.findAllDesc().stream()
                                                .map(NoticeListResponse::new)
                                                .collect(Collectors.toList());
    }




}
