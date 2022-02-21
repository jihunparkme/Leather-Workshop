package com.leather.workshop.domain.review.service;

import com.leather.workshop.domain.login.domain.User;
import com.leather.workshop.domain.login.domain.UserRepository;
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

    private final UserRepository userRepository;

    @Transactional
    public void save(ReviewDto.Request request) {
        User user = userRepository.findById(request.getUserId()).get();
        request.setNickname(user.getName());

        reviewRepository.save(request.toEntity(user));
    }

//    @Transactional
//    public Long update(Long id, NoticeUpdateRequest noticeUpdateRequest) {
//        Notice notice = noticeRepository.findById(id)
//                .orElseThrow(() -> new NoticeNotFoundException("해당 공지사항이 없습니다. id=" + id));
//
//        notice.update(noticeUpdateRequest.getTitle(), noticeUpdateRequest.getContents());
//
//        return id;
//    }
//
//    @Transactional
//    public void delete(Long id) {
//        Notice posts = noticeRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("해당 공지사항이 없습니다. id=" + id));
//
//        noticeRepository.delete(posts);
//    }

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
