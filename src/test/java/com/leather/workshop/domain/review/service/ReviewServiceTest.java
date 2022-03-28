package com.leather.workshop.domain.review.service;

import com.leather.workshop.domain.login.domain.Role;
import com.leather.workshop.domain.login.domain.User;
import com.leather.workshop.domain.login.domain.UserRepository;
import com.leather.workshop.domain.review.domain.Review;
import com.leather.workshop.domain.review.domain.ReviewRepository;
import com.leather.workshop.domain.review.web.dto.ReviewDto;
import com.leather.workshop.global.common.util.service.MailUtilService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MailUtilService mailUtilService;

    @InjectMocks
    private ReviewService reviewService;

    private Long id = 2L;
    private Long userId = 1L;
    private String nickname = "Aaron";
    private String contents = "정말 강추합니다.";

    private User user = User.builder()
            .id(userId)
            .name("Aaron")
            .email("jihunpark.tech@gmail.com")
            .picture("")
            .role(Role.USER)
            .build();

    private Review review = Review.builder()
            .id(id)
            .user(user)
            .nickname(nickname)
            .contents(contents).build();


    @Test
    @DisplayName("리뷰 등록 성공")
    void save() {

        ReviewDto.Request request = ReviewDto.Request.builder()
            .userId(userId)
            .nickname(nickname)
            .contents(contents).build();

        given(userRepository.findById(any())).willReturn(Optional.of(user));
        given(reviewRepository.save(any())).willReturn(Optional.of(review).get());

        reviewService.save(request);

        verify(userRepository).findById(any());
        verify(reviewRepository).save(any());
        verify(mailUtilService).sendMail(any());
    }

    @Test
    @DisplayName("리뷰 수정 성공")
    void update() {
        ReviewDto.Request request = ReviewDto.Request.builder()
                .userId(userId)
                .nickname(nickname)
                .contents(contents).build();

        given(reviewRepository.findById(any())).willReturn(Optional.of(review));

        reviewService.update(id, request);

        verify(reviewRepository).findById(any());
    }

    @Test
    @DisplayName("리뷰 삭제 성공")
    void delete() {
        given(reviewRepository.findById(any())).willReturn(Optional.of(review));

        reviewService.delete(id);

        verify(reviewRepository).findById(any());
        verify(reviewRepository).delete(any());
    }

    @Test
    @DisplayName("리뷰 조회 성공")
    void findById() {
        given(reviewRepository.findById(any())).willReturn(Optional.of(review));

        reviewService.findById(id);

        verify(reviewRepository).findById(any());
    }

    @Test
    @DisplayName("전체 리뷰 내림차순 조회 성공")
    void findAllDesc() {
        given(reviewRepository.findAllDesc()).willReturn(new ArrayList<>(Arrays.asList(review)));

        reviewService.findAllDesc();

        verify(reviewRepository).findAllDesc();
    }
}