package com.leather.workshop.domain.review.web.dto;

import com.leather.workshop.domain.review.domain.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
