package com.leather.workshop.domain.notice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoticeNotFoundException extends IllegalArgumentException {
    public NoticeNotFoundException(String message) {
        super(message);
    }
}
