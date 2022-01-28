package com.leather.workshop.domain.notice.exception;

public class NoticeNotFoundException extends IllegalArgumentException {
    public NoticeNotFoundException(String message) {
        super(message);
    }
}
