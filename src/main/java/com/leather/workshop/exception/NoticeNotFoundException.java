package com.leather.workshop.exception;

public class NoticeNotFoundException extends IllegalArgumentException {
    public NoticeNotFoundException(String message) {
        super(message);
    }
}
