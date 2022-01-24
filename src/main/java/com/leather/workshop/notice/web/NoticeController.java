package com.leather.workshop.notice.web;

import com.leather.workshop.notice.service.NoticeService;
import com.leather.workshop.notice.web.dto.NoticeSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
@Controller(value = "/notice")
@RequestMapping(value = "/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping("/add")
    public String save(@RequestBody NoticeSaveForm noticeSaveForm,
                     HttpServletRequest request, HttpServletResponse response) {

        log.info("save form notice");
        Long id = noticeService.save(noticeSaveForm);

        return "redirect:/notice/" + id;
    }
}
