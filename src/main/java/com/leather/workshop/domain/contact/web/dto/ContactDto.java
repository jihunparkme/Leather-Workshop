package com.leather.workshop.domain.contact.web.dto;

import com.leather.workshop.domain.contact.domain.Contact;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

public class ContactDto {

    @Data
    @NoArgsConstructor
    public class ContactRequest {
        @NotBlank
        private String name;
        private String email;
        @NotBlank
        private String phoneNumber;
        @NotBlank
        private String title;
        @NotBlank
        private String contents;

        public Contact toEntity() {
            return Contact.builder()
                    .name(name)
                    .email(email)
                    .phoneNumber(phoneNumber)
                    .title(title)
                    .contents(contents)
                    .build();
        }
    }
}
