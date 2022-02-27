package com.leather.workshop.domain.review.web;

import com.leather.workshop.domain.review.domain.Review;
import com.leather.workshop.domain.review.service.ReviewService;
import com.leather.workshop.domain.review.web.dto.ReviewDto;
import com.leather.workshop.global.common.response.BasicResponse;
import com.leather.workshop.global.common.util.service.CheckAuthorityService;
import com.leather.workshop.global.config.security.LoginUser;
import com.leather.workshop.global.config.security.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    private final CheckAuthorityService checkAuthorityService;

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

    @GetMapping("/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String edit(@PathVariable Long id,
                       @LoginUser SessionUser user,
                       Model model) {

        ReviewDto.Response review = reviewService.findById(id);
        if (checkAuthorityService.isNotSameUserIdAndIsNotAdmin(review.getUserId(), user, model)) {
            return "/common/util/message-redirect";
        }

        model.addAttribute("review", review);
        return "/review/review-edit";
    }

    @PostMapping("/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String edit(@PathVariable Long id,
                       @Validated @ModelAttribute("review") ReviewDto.Request form,
                       @LoginUser SessionUser user,
                       BindingResult bindingResult,
                       Model model) {

        if (checkAuthorityService.isNotSameUserIdAndIsNotAdmin(form.getUserId(), user, model)) {
            return "/common/util/message-redirect";
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "/review/review-edit";
        }

        reviewService.update(id, form);

        return "redirect:/review";
    }

    @ResponseBody
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BasicResponse> delete(@PathVariable Long id,
                                                @LoginUser SessionUser user) {

        ReviewDto.Response review = reviewService.findById(id);
        BasicResponse basicResponse;

        if (checkAuthorityService.isNotSameUserIdAndIsNotAdmin(review.getUserId(), user)) {
            basicResponse = BasicResponse.builder()
                                        .code(HttpStatus.FORBIDDEN.value())
                                        .httpStatus(HttpStatus.FORBIDDEN)
                                        .message("접근 권한이 없습니다.").build();
        } else {
            reviewService.delete(id);

            basicResponse = BasicResponse.builder()
                                        .code(HttpStatus.OK.value())
                                        .httpStatus(HttpStatus.OK)
                                        .message("후기가 삭제되었습니다.").build();
        }

        return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
    }
}
