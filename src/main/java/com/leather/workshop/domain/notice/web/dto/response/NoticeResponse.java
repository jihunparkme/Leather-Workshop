package com.leather.workshop.domain.notice.web.dto.response;

import com.leather.workshop.domain.notice.domain.Notice;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NoticeResponse {

    private Long id;
    private Long memberId;
    private String title;
    private String contents;
    private Long hits;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;

    public NoticeResponse(Notice notice) {
        this.id = notice.getId();
        this.memberId = notice.getMemberId();
        this.title = notice.getTitle();
        this.contents = notice.getContents();
        this.hits = notice.getHits();
        this.createDateTime = notice.getCreateDateTime();
        this.updateDateTime = notice.getUpdateDateTime();
    }
}
