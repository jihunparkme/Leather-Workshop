package com.leather.workshop.domain.notice.web.dto.request;

import com.leather.workshop.domain.notice.domain.Notice;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class NoticeSaveRequest {

    private Long userId;

    @NotBlank
    private String title;

    @NotBlank
    private String contents;

    @Column(columnDefinition = "BIGINT default 0")
    private Long hits;

    @Builder
    public NoticeSaveRequest(Long userId, String title, String contents, Long hits) {
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.hits = hits;
    }

    public Notice toEntity() {
        return Notice.builder()
                .userId(userId)
                .title(title)
                .contents(contents)
                .hits(hits)
                .build();
    }
}
