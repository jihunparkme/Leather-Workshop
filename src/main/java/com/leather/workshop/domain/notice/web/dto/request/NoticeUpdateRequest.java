package com.leather.workshop.domain.notice.web.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class NoticeUpdateRequest {

    @NotNull
    private Long id;

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
