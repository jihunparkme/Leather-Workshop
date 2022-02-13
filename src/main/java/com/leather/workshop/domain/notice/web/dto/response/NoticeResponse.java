package com.leather.workshop.domain.notice.web.dto.response;

import com.leather.workshop.domain.notice.domain.Notice;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class NoticeResponse {

    private Long id;
    private Long userId;
    private String title;
    private String contents;
    private Long hits;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;

    public NoticeResponse(Notice notice) {
        this.id = notice.getId();
        this.userId = notice.getUserId();
        this.title = notice.getTitle();
        this.contents = notice.getContents();
        this.hits = notice.getHits();
        this.createdDateTime = notice.getCreatedDateTime();
        this.modifiedDateTime = notice.getModifiedDateTime();
    }
}
