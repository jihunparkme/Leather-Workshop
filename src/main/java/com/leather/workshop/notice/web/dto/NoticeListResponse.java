package com.leather.workshop.notice.web.dto;

import com.leather.workshop.notice.domain.Notice;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NoticeListResponse {
    private Long id;
    private Long memberId;
    private String title;
    private String contents;
    private Long hits;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;

    public NoticeListResponse(Notice entity) {
        this.id = entity.getId();
        this.memberId = entity.getMemberId();
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.hits = entity.getHits();
        this.createDateTime = entity.getCreateDateTime();
        this.updateDateTime = entity.getUpdateDateTime();
    }
}
