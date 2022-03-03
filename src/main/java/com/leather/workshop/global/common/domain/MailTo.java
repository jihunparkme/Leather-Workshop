package com.leather.workshop.global.common.domain;

import lombok.Getter;

import java.util.List;
import java.util.Optional;

@Getter
public class MailTo {

    private String title;
    private String contents;
    private Optional<List<String>> addressList;

    public MailTo(String title, String contents, Optional<List<String>> addressList) {
        this.title = "[Com'e] " + title;
        this.contents = contents;
        this.addressList = addressList;
    }
}
