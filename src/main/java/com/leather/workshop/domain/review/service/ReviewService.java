package com.leather.workshop.domain.review.service;

import com.leather.workshop.domain.review.domain.Review;
import com.leather.workshop.domain.review.domain.ReviewRepository;
import com.leather.workshop.domain.review.web.dto.ReviewDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

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
}
