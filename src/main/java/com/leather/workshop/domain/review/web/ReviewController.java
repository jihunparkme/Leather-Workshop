package com.leather.workshop.domain.review.web;

import com.leather.workshop.domain.review.domain.Review;
import com.leather.workshop.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
