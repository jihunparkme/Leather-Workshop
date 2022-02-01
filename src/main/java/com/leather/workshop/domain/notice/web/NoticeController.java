package com.leather.workshop.domain.notice.web;

import com.leather.workshop.domain.notice.domain.Notice;
import com.leather.workshop.domain.notice.service.NoticeService;
import com.leather.workshop.domain.notice.web.dto.request.NoticeSaveRequest;
import com.leather.workshop.domain.notice.web.dto.request.NoticeUpdateRequest;
import com.leather.workshop.domain.notice.web.dto.response.NoticeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "notice/notice-list";
    }

    @GetMapping("{id}")
    public String view(@PathVariable Long id, Model model) {

        model.addAttribute("notice", noticeService.findById(id));
        return "notice/notice-view";
    }

    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute("notice", new NoticeResponse());
        return "notice/notice-add";
    }

    @PostMapping("/add")
    public String edit(@Validated @ModelAttribute("notice") NoticeSaveRequest form,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "notice/notice-add";
        }

        form.setMemberId(1L);
        form.setHits(0L);
        Long id = noticeService.save(form);
        redirectAttributes.addAttribute("id", id);
        redirectAttributes.addAttribute("status", true);

        return "redirect:/notice/{id}";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {

        model.addAttribute("notice", noticeService.findById(id));
        return "notice/notice-edit";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id,
                       @Validated @ModelAttribute("notice") NoticeUpdateRequest form,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "notice/notice-edit";
        }

        noticeService.update(id, form);
        redirectAttributes.addAttribute("status", true);

        return "redirect:/notice/{id}";
    }
}
