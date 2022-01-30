package com.leather.workshop.domain.notice.web;

import com.leather.workshop.domain.notice.domain.Notice;
import com.leather.workshop.domain.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("")
    public String list(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "5") Integer size,
            Model model) {

        Page<Notice> noticeListPage = noticeService.findAllSortByIdDescPaging(page, size);

        model.addAttribute("noticeListPage", noticeListPage);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        return "notice/notice-list";
    }

//    @PostMapping("/save")
//    public String save(@RequestBody NoticeSaveRequest noticeSaveRequest,
//                     HttpServletRequest request, HttpServletResponse response) {
//
//        log.info("save form notice");
//        Long id = noticeService.save(noticeSaveRequest);
//
//        return "redirect:/notice/notice-view/" + id;
//    }
}
