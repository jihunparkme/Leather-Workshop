package com.leather.workshop.global.common.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class PageResponse extends BasicResponse {

    private Long totalElements;
    private Integer totalPages;
}
