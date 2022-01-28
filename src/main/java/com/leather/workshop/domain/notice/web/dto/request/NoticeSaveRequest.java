package com.leather.workshop.domain.notice.web.dto.request;

import com.leather.workshop.domain.notice.domain.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeSaveRequest {

    private Long memberId;
    private String title;
    private String contents;
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
