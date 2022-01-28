package com.leather.workshop.domain.notice.web;

import com.leather.workshop.domain.notice.service.NoticeService;
import com.leather.workshop.domain.notice.web.dto.request.NoticeSaveRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/list")
    public String view() {
        return "notice/list";
    }

    @PostMapping("")
    public String save(@RequestBody NoticeSaveRequest noticeSaveRequest,
                     HttpServletRequest request, HttpServletResponse response) {

        log.info("save form notice");
        Long id = noticeService.save(noticeSaveRequest);

        return "redirect:/notice/" + id;
    }
}
