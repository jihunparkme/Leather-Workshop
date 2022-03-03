package com.leather.workshop.global.common.util.service;

import com.leather.workshop.global.common.domain.MailTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

@Service
public class MailUtilService {

    @Value("${spring.mail.username}")
    private String senderAddress;

    @Value("${admin.mail.address}")
    private String adminAddress;

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(MailTo mailTo) {

        MimeMessage message = getCustomContents(mailTo.getTitle(), mailTo.getContents(), mailTo.getAddressList());
        mailSender.send(message);
    }

    private MimeMessage getCustomContents(String title, String subject, Optional<List<String>> addressList) {

        MimeMessage message = mailSender.createMimeMessage();
        try {
            message.setSubject(title);
            if (addressList.isEmpty()) {
                message.setRecipients(Message.RecipientType.TO,
                                        InternetAddress.parse(adminAddress));
            } else {
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(addressList.get().stream().collect(joining(","))));
            }
            message.setFrom(new InternetAddress(senderAddress, MimeUtility.encodeText("Com'e", "UTF-8", "B")));
            message.setContent(subject, "text/html;charset=UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return message;
    }
}
