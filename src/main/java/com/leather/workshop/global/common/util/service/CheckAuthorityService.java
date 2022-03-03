package com.leather.workshop.global.common.util.service;

import com.leather.workshop.domain.login.domain.Role;
import com.leather.workshop.global.common.util.web.dto.AlertMessage;
import com.leather.workshop.global.config.security.dto.SessionUser;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class CheckAuthorityService {

    public Boolean isNotSameUserIdAndIsNotAdmin(Long entityUserId, SessionUser user) {
        if (isNotSameUserId(entityUserId, user.getId()) && isNotAdmin(user.getRole())) {
            return true;
        }

        return false;
    }

    public Boolean isNotSameUserIdAndIsNotAdmin(Long entityUserId, SessionUser user, Model model) {
        if (isNotSameUserId(entityUserId, user.getId()) && isNotAdmin(user.getRole())) {
            model.addAttribute("error", new AlertMessage("접근 권한이 없습니다."));
            return true;
        }

        return false;
    }

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

    public Boolean isNotAdmin(Role role) {
        if (role.equals(Role.ADMIN)) {
            return false;
        }
        return true;
    }
}