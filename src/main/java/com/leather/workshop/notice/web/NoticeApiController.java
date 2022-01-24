package com.leather.workshop.notice.web;

import com.leather.workshop.notice.service.NoticeService;
import com.leather.workshop.notice.web.dto.NoticeSaveRequest;
import com.leather.workshop.notice.web.dto.NoticeUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/notice")
public class NoticeApiController {

    private final NoticeService noticeService;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long save(@RequestBody NoticeSaveRequest noticeSaveRequest) {

        log.info("save api notice");
        return noticeService.save(noticeSaveRequest);
    }

    @PutMapping(value= "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long update(@PathVariable Long id, @RequestBody NoticeUpdateRequest noticeUpdateRequest) {
        return noticeService.update(id, noticeUpdateRequest);
    }
}
