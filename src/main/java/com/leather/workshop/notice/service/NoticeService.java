package com.leather.workshop.notice.service;

import com.leather.workshop.notice.domain.NoticeRepository;
import com.leather.workshop.notice.web.dto.NoticeSaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    public Long save(NoticeSaveForm noticeSaveForm) {
        return noticeRepository.save(noticeSaveForm.toEntity()).getId();
    }
}
