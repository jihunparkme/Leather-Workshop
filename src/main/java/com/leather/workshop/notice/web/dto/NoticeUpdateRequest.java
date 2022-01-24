package com.leather.workshop.notice.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class NoticeUpdateRequest {

    private String title;
    private String contents;
    private LocalDateTime updateDateTime;

    @Builder
    public NoticeUpdateRequest(String title, String contents, LocalDateTime updateDateTime) {
        this.title = title;
        this.contents = contents;
        this.updateDateTime = updateDateTime;
    }
}
