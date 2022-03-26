package com.leather.workshop.domain.review.service;

import com.leather.workshop.domain.login.domain.User;
import com.leather.workshop.domain.login.domain.UserRepository;
import com.leather.workshop.domain.review.domain.Review;
import com.leather.workshop.domain.review.domain.ReviewRepository;
import com.leather.workshop.domain.review.web.dto.ReviewDto;
import com.leather.workshop.global.common.domain.MailTo;
import com.leather.workshop.global.common.exception.EntityNotFoundException;
import com.leather.workshop.global.common.util.service.MailUtilService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    private final MailUtilService mailUtilService;

    @Transactional
    public void save(ReviewDto.Request request) {
        User user = userRepository.findById(request.getUserId()).get();
        request.setNickname(user.getName());

        reviewRepository.save(request.toEntity(user));
        sendNotificationEmailToAdmin(request);
    }

    @Transactional
    public void update(Long id, ReviewDto.Request request) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 리뷰입니다. id=" + id));

        review.update(request.getContents());
    }

    @Transactional
    public void delete(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리뷰입니다. id=" + id));

        reviewRepository.delete(review);
    }

    @Transactional(readOnly = true)
    public ReviewDto.Response findById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 리뷰입니다. id=" + id));

        return new ReviewDto.Response(review);
    }

    @Transactional(readOnly = true)
    public List<ReviewDto.Response> findAllDesc() {
        return reviewRepository.findAllDesc().stream()
                                            .map(ReviewDto.Response::new)
                                            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<Review> findAllSortByIdDescPaging(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return reviewRepository.findAll(pageable);
    }

    private void sendNotificationEmailToAdmin(ReviewDto.Request request) {

        String title = "리뷰가 등록되었습니다.";

        Map<String, String> contentsMap = new LinkedHashMap<>();
        contentsMap.put("작성자", request.getNickname());
        contentsMap.put("내용", request.getContents().replaceAll("(\r\n|\n)", "<br/>"));

        mailUtilService.sendMail(new MailTo(title, mailUtilService.getContents(title, contentsMap), Optional.empty()));
    }
}
