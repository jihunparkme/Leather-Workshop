package com.leather.workshop.domain.notice.web.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class NoticeUpdateRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String contents;

    @Builder
    public NoticeUpdateRequest(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
