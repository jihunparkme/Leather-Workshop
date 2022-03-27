package com.leather.workshop.domain.contact.web.dto;

import com.leather.workshop.domain.contact.domain.Contact;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ContactDto {

    @Data
    @NoArgsConstructor
    public class Request {

        @NotBlank
        @Size(max = 30)
        private String name;

        @Size(max = 50)
        private String email;

        @NotBlank
        @Size(max = 20)
        private String phoneNumber;

        @NotBlank
        @Size(max = 100)
        private String title;

        @NotBlank
        @Size(max = 20000)
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
