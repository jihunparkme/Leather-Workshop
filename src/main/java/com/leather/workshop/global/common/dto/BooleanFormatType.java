package com.leather.workshop.global.common.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BooleanFormatType {

    Y(1, true),
    N(0, false);

    private final Integer num;
    private final Boolean bool;
}
