package com.leather.workshop.global.common.util.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AlertMessage {
    String message = "";
    String redirectUri = "";

    public AlertMessage(String message) {
        this.message = message;
    }

    public AlertMessage(String message, String redirectUri) {
        this.message = message;
        this.redirectUri = redirectUri;
    }
}