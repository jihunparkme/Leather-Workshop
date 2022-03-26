package com.leather.workshop.domain.contact.web;

import com.leather.workshop.domain.contact.service.ContactService;
import com.leather.workshop.domain.contact.web.dto.ContactDto;
import com.leather.workshop.global.common.response.BasicResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/contact")
public class ContactController {

    private final ContactService contactService;

    @GetMapping("")
    public String add() {
        return "contact/contact";
    }

    @ResponseBody
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BasicResponse> add(@ModelAttribute(value = "contact") ContactDto.Request contact) {

        Long saveId = contactService.save(contact);
        BasicResponse basicResponse = BasicResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("문의가 등록되었습니다.")
                .count(1)
                .result(Arrays.asList(saveId))
                .build();

        return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
    }
}
