package com.leather.workshop.domain.contact.service;

import com.leather.workshop.domain.contact.domain.ContactRepository;
import com.leather.workshop.domain.contact.web.dto.ContactDto;
import com.leather.workshop.global.common.domain.MailTo;
import com.leather.workshop.global.common.util.service.MailUtilService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
@Service
public class ContactService {

    private final ContactRepository contactRepository;

    private final MailUtilService mailUtilService;

    @Transactional
    public Long save(ContactDto.Request contact) {

        sendNotificationEmailToAdmin(contact);

        return contactRepository.save(contact.toEntity()).getId();
    }

    private void sendNotificationEmailToAdmin(ContactDto.Request request) {

        String title = "문의가 등록되었습니다.";

        Map<String, String> contentsMap = new LinkedHashMap<>();
        contentsMap.put("이름", request.getName());
        contentsMap.put("휴대폰 번호", request.getPhoneNumber());
        contentsMap.put("이메일", request.getEmail());
        contentsMap.put("제목", request.getTitle());
        contentsMap.put("내용", request.getContents().replaceAll("(\r\n|\n)", "<br/>"));

        mailUtilService.sendMail(new MailTo(title, mailUtilService.getContents(title, contentsMap), Optional.empty()));
    }
}
