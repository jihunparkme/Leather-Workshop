package com.leather.workshop.domain.review.web.dto;

import com.leather.workshop.domain.login.domain.User;
import com.leather.workshop.domain.review.domain.Review;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class ReviewDto {

    @Getter
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private Long userId;
        private String userName;
        private String contents;
        private LocalDateTime createdDateTime;
        private LocalDateTime modifiedDateTime;

        public Response(Review entity) {
            this.id = entity.getId();
            this.userId = entity.getUser().getId();
            this.userName = entity.getUser().getName();
            this.contents = entity.getContents();
            this.createdDateTime = entity.getCreatedDateTime();
            this.modifiedDateTime = entity.getModifiedDateTime();
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        private Long userId;
        private String nickname;
        @NotBlank
        @Size(max = 20000)
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
