package com.leather.workshop.notice.web.dto;

import com.leather.workshop.notice.domain.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class NoticeSaveForm {

    private Long memberId;
    private String title;
    private String contents;
    private Long hits;
    private LocalDateTime createDateTime;

    @Builder
    public NoticeSaveForm(Long memberId, String title, String contents, Long hits, LocalDateTime createDateTime) {
        this.memberId = memberId;
        this.title = title;
        this.contents = contents;
        this.hits = hits;
        this.createDateTime = createDateTime;
    }

    public Notice toEntity() {
        return Notice.builder()
                .memberId(memberId)
                .title(title)
                .contents(contents)
                .hits(hits)
                .createDateTime(createDateTime)
                .build();
    }
}
