package com.leather.workshop.domain.review.web.dto;

import com.leather.workshop.domain.login.domain.User;
import com.leather.workshop.domain.review.domain.Review;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class ReviewDto {

    @Getter
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private String userName;
        private String contents;
        private LocalDateTime createdDateTime;
        private LocalDateTime modifiedDateTime;

        public Response(Review entity) {
            this.id = entity.getId();
            this.userName = entity.getUser().getName();
            this.contents = entity.getContents();
            this.createdDateTime = entity.getCreatedDateTime();
            this.modifiedDateTime = entity.getModifiedDateTime();
        }
    }

    @Data
    @NoArgsConstructor
    public static class Request {

        private Long userId;
        private String nickname;
        @NotBlank
        private String contents;

        @Builder
        public Request(Long userId, String contents) {
            this.userId = userId;
            this.contents = contents;
        }

        public Review toEntity(User user) {
            return Review.builder()
                    .user(user)
                    .nickname(nickname)
                    .contents(contents)
                    .build();
        }
    }
}
