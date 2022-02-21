package com.leather.workshop.domain.review.web;

import com.leather.workshop.domain.review.domain.Review;
import com.leather.workshop.domain.review.service.ReviewService;
import com.leather.workshop.domain.review.web.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("")
    public String list(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            Model model) {

        Page<Review> reviewListPage = reviewService.findAllSortByIdDescPaging(page, size);

        model.addAttribute("reviewListPage", reviewListPage);
        model.addAttribute("page", page);

        return "review/review-list";
    }

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String add(Model model) {

        model.addAttribute("review", new ReviewDto.Response());
        return "review/review-add";
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String add(@Validated @ModelAttribute("review") ReviewDto.Request review,
                      BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "review/review-add";
        }

        reviewService.save(review);

        return "redirect:/review";
    }

//    @GetMapping("/{id}/edit")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
//    public String edit(@PathVariable Long id, Model model) {
//
//        model.addAttribute("notice", noticeService.findById(id));
//        return "notice/notice-edit";
//    }
//
//    @PostMapping("/{id}/edit")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
//    public String edit(@PathVariable Long id,
//                       @Validated @ModelAttribute("notice") NoticeUpdateRequest form,
//                       BindingResult bindingResult,
//                       RedirectAttributes redirectAttributes) {
//
//        if (bindingResult.hasErrors()) {
//            log.info("errors={}", bindingResult);
//            return "notice/notice-edit";
//        }
//
//        noticeService.update(id, form);
//        redirectAttributes.addAttribute("status", true);
//
//        return "redirect:/notice/{id}";
//    }
//
//    @PostMapping("/{id}/delete")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
//    public String delete(@PathVariable Long id) {
//
//        noticeService.delete(id);
//        return "redirect:/notice";
//    }
}
