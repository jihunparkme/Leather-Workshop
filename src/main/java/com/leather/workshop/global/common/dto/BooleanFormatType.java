package com.leather.workshop.global.common.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BooleanFormatType {

    YES(1, "Y", true),
    NO(0, "N", false);

    private final Integer num;
    private final String alpha;
    private final Boolean bool;
}
