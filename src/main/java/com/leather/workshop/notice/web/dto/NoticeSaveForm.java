package com.leather.workshop.notice.web.dto;

import com.leather.workshop.notice.domain.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
public class NoticeSaveForm {

    private Long memberId;
    private String title;
    private String contents;
    private Long hits;
    private LocalDateTime createDateTime;

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
