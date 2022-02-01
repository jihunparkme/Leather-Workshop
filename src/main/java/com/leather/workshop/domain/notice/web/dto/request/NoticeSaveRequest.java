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

    private Long memberId;

    @NotBlank
    private String title;

    @NotBlank
    private String contents;

    @Column(columnDefinition = "BIGINT default 0")
    private Long hits;

    @Builder
    public NoticeSaveRequest(Long memberId, String title, String contents, Long hits) {
        this.memberId = memberId;
        this.title = title;
        this.contents = contents;
        this.hits = hits;
    }

    public Notice toEntity() {
        return Notice.builder()
                .memberId(memberId)
                .title(title)
                .contents(contents)
                .hits(hits)
                .build();
    }
}
