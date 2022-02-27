package com.leather.workshop.global.common.util.service;

import com.leather.workshop.global.common.util.web.dto.AlertMessage;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class CheckAuthorityService {

    public Boolean isNotSameUserId(Long entityUserId, Long userId) {
        if (entityUserId != userId) {
            return true;
        }

        return false;
    }

    public Boolean isNotSameUserId(Long entityUserId, Long userId, Model model) {
        if (entityUserId != userId) {
            model.addAttribute("error", new AlertMessage("접근 권한이 없습니다."));
            return true;
        }

        return false;
    }
}
