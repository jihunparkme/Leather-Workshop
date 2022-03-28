package com.leather.workshop.domain.notice.web.dto;

import com.leather.workshop.domain.notice.domain.Notice;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class NoticeDto {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveRequest {

        private Long userId;

        @NotBlank
        @Size(max = 100)
        private String title;

        @NotBlank
        @Size(max = 20000)
        private String contents;

        @Column(columnDefinition = "BIGINT default 0")
        private Long hits;

        public Notice toEntity() {
            return Notice.builder()
                    .userId(userId)
                    .title(title)
                    .contents(contents)
                    .hits(hits)
                    .build();
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateRequest {

        @NotNull
        private Long id;

        @NotBlank
        @Size(max = 100)
        private String title;

        @NotBlank
        @Size(max = 20000)
        private String contents;

        @Builder
        public UpdateRequest(String title, String contents) {
            this.title = title;
            this.contents = contents;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Response {

        private Long id;
        private Long userId;
        private String title;
        private String contents;
        private Long hits;
        private LocalDateTime createdDateTime;
        private LocalDateTime modifiedDateTime;

        public Response(Notice notice) {
            this.id = notice.getId();
            this.userId = notice.getUserId();
            this.title = notice.getTitle();
            this.contents = notice.getContents();
            this.hits = notice.getHits();
            this.createdDateTime = notice.getCreatedDateTime();
            this.modifiedDateTime = notice.getModifiedDateTime();
        }
    }
}
