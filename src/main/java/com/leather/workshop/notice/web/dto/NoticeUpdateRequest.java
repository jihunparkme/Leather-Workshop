package com.leather.workshop.notice.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeUpdateRequest {

    private String title;
    private String contents;

    @Builder
    public NoticeUpdateRequest(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
