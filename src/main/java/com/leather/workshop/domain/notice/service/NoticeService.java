package com.leather.workshop.domain.notice.service;

import com.leather.workshop.domain.notice.exception.NoticeNotFoundException;
import com.leather.workshop.domain.notice.domain.Notice;
import com.leather.workshop.domain.notice.domain.NoticeRepository;
import com.leather.workshop.domain.notice.web.dto.response.NoticeListResponse;
import com.leather.workshop.domain.notice.web.dto.response.NoticeResponse;
import com.leather.workshop.domain.notice.web.dto.request.NoticeSaveRequest;
import com.leather.workshop.domain.notice.web.dto.request.NoticeUpdateRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    public Long save(NoticeSaveRequest noticeSaveRequest) {
        return noticeRepository.save(noticeSaveRequest.toEntity()).getId();
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

    @Transactional(readOnly = true)
    public Page<Notice> findAllSortByIdDescPaging(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return noticeRepository.findAll(pageable);
    }
}
