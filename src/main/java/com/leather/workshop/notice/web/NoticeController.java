package com.leather.workshop.notice.web;

import com.leather.workshop.notice.service.NoticeService;
import com.leather.workshop.notice.web.dto.NoticeSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
@Controller(value = "/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping("/add")
    public String save(@RequestBody NoticeSaveRequestDto noticeSaveForm,
                     HttpServletRequest request, HttpServletResponse response) {

        log.info("save form notice");
        Long id = noticeService.save(noticeSaveForm);

        return "redirect:/notice/" + id;
    }

    @ResponseBody
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long saveApi(@RequestBody NoticeSaveRequestDto noticeSaveForm) {

        log.info("save api notice");

        return noticeService.save(noticeSaveForm);
    }
}
