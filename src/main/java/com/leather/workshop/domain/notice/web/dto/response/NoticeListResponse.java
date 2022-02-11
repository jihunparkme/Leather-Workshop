package com.leather.workshop.domain.notice.web.dto.response;

import com.leather.workshop.domain.notice.domain.Notice;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NoticeListResponse {
    private Long id;
    private Long userId;
    private String title;
    private String contents;
    private Long hits;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;

    public NoticeListResponse(Notice entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.hits = entity.getHits();
        this.createDateTime = entity.getCreatedDateTime();
        this.updateDateTime = entity.getModifiedDateTime();
    }
}
