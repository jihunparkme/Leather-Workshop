package com.leather.workshop.domain.notice.web;

import com.leather.workshop.domain.notice.service.NoticeService;
import com.leather.workshop.domain.notice.web.dto.NoticeDto;
import com.leather.workshop.domain.notice.web.dto.NoticeListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/notice")
public class NoticeApiController {

    private final NoticeService noticeService;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Long save(@RequestBody NoticeDto.SaveRequest noticeSaveRequest) {
        log.info("save api notice");
        return noticeService.save(noticeSaveRequest);
    }

    @PutMapping(value= "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Long update(@PathVariable Long id, @RequestBody NoticeDto.UpdateRequest noticeUpdateRequest) {
        return noticeService.update(id, noticeUpdateRequest);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Long delete(@PathVariable Long id) {
        noticeService.delete(id);
        return id;
    }

    @GetMapping(value= "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoticeDto.Response search(@PathVariable Long id) {
        return noticeService.findById(id);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NoticeListResponse> findAll() {
        return noticeService.findAllDesc();
    }
}