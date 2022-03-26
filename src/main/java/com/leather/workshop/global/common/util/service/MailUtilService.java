package com.leather.workshop.global.common.util.service;

import com.leather.workshop.global.common.domain.MailTo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

@RequiredArgsConstructor
@Service
public class MailUtilService {

    @Value("${spring.mail.username}")
    private String senderAddress;

    @Value("${admin.mail.address}")
    private String adminAddress;

    private final JavaMailSender mailSender;

    public void sendMail(MailTo mailTo) {

        MimeMessage message = getCustomContents(mailTo.getTitle(), mailTo.getContents(), mailTo.getAddressList());
        mailSender.send(message);
    }

    public String getContents(String title, Map<String, String> contentsMap) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        StringBuffer sb = new StringBuffer();
        sb.append("<table bgcolor='#eeeeee' cellpadding='10' cellspacing='0' border='0' width='100%' align='center' style='max-width: 500px; font-family: arial; font-size: 13px; color: #555555;'>");
        sb.append("  <tr>");
        sb.append("    <td>");
        sb.append("      <table width='100%' cellspacing='5' cellpadding='5'>");
        sb.append("        <tr>");
        sb.append("          <td align='left' valign='baseline' style='font-size: 17px;'>");
        sb.append(title);
        sb.append("          </td>");
        sb.append("          <td align='right' valign='baseline' style='color: #df691a;'>");
        sb.append("            Com'e");
        sb.append("          </td>");
        sb.append("        </tr>");
        sb.append("      </table>");
        sb.append("    </td>");
        sb.append("  </tr>");
        sb.append("  <tr>");
        sb.append("    <td>");
        sb.append("      <table width='100%' cellpadding='10' cellspacing='2' bgcolor='#eeeeee' border='0' align='left'>");

        for (String key : contentsMap.keySet()) {
            sb.append("        <tr width='100%' bgcolor='#ffffff'>");
            sb.append("          <th width='25%'>");
            sb.append(key);
            sb.append("          </th>");
            sb.append("          <td width='75%'>");
            sb.append(contentsMap.get(key));
            sb.append("          </td>");
            sb.append("        </tr>");
        }

        sb.append("        <tr width='100%' bgcolor='#ffffff'>");
        sb.append("          <th width='25%'>");
        sb.append("발송시간");
        sb.append("          </th>");
        sb.append("          <td width='75%'>");
        sb.append(LocalDateTime.now().format(formatter));
        sb.append("          </td>");
        sb.append("        </tr>");
        sb.append("      </table>");
        sb.append("    </td>");
        sb.append("  </tr>");
        sb.append("  <tr>");
        sb.append("    <td>");
        sb.append("      <table width='100%' cellspacing='5' cellpadding='5' style='font-size: 11px;'>");
        sb.append("        <tr>");
        sb.append("          <td align='left' valign='baseline'>");
        sb.append("          </td>");
        sb.append("          <td align='right' valign='baseline'>&copy; <a href='http://localhost:8080/' target='_blank' title='Com'e' style='text-decoration: none; color: #555;'>Com'e</a></td>");
        sb.append("        </tr>");
        sb.append("      </table>");
        sb.append("    </td>");
        sb.append("  </tr>");
        sb.append("</table>");

        return sb.toString();
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
