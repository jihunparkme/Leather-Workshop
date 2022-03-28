package com.leather.workshop.domain.contact.service;

import com.leather.workshop.domain.contact.domain.Contact;
import com.leather.workshop.domain.contact.domain.ContactRepository;
import com.leather.workshop.domain.contact.web.dto.ContactDto;
import com.leather.workshop.global.common.util.service.MailUtilService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @Mock
    Contact contact;


    private ContactRepository contactRepository = mock(ContactRepository.class);
    private MailUtilService mailUtilService = mock(MailUtilService.class);
    private ContactService contactService = new ContactService(contactRepository, mailUtilService);

    Long id = 1L;
    String name = "이름";
    String email = "test@gmail.com";
    String phoneNumber = "010-1234-1234";
    String title = "테스트 제목";
    String contents = "테스트 내용";

    @Test
    @DisplayName("문의 저장")
    void save() {
        ContactDto.Request request = ContactDto.Request.builder()
                .name(name)
                .email(email)
                .phoneNumber(phoneNumber)
                .title(title)
                .contents(contents)
                .build();

        Contact contact = new Contact(id, name, email, phoneNumber, title, contents);

        given(contactRepository.save(any())).willReturn(Optional.of(contact).get());

        Long save = contactService.save(request);

        assertThat(save).isEqualTo(1L);
    }
}