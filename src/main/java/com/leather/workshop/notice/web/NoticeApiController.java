package com.leather.workshop.notice.web;

import com.leather.workshop.notice.service.NoticeService;
import com.leather.workshop.notice.web.dto.NoticeSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/notice")
public class NoticeApiController {

    private final NoticeService noticeService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long saveApi(@RequestBody NoticeSaveForm noticeSaveForm) {

        log.info("save api notice");

        return noticeService.save(noticeSaveForm);
    }
}
