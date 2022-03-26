package com.leather.workshop.domain.contact.service;

import com.leather.workshop.domain.contact.domain.ContactRepository;
import com.leather.workshop.domain.contact.web.dto.ContactDto;
import com.leather.workshop.global.common.domain.MailTo;
import com.leather.workshop.global.common.util.service.MailUtilService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        StringBuffer mailContents = new StringBuffer();
        mailContents.append("<h4>이름</h4>");
        mailContents.append("<p>").append(request.getName()).append("</p><br/>");
        mailContents.append("<h4>휴대폰 번호</h4>");
        mailContents.append("<p>").append(request.getPhoneNumber()).append("</p><br/>");
        mailContents.append("<h4>제목</h4>");
        mailContents.append("<p>").append(request.getTitle()).append("</p><br/>");
        mailContents.append("<h4>내용</h4>");
        mailContents.append("<p>").append(request.getContents().replaceAll("(\r\n|\n)", "<br/>")).append("</p><br/>");
        mailUtilService.sendMail(new MailTo("문의가 등록되었습니다.", mailContents.toString(), Optional.empty()));
    }
}
