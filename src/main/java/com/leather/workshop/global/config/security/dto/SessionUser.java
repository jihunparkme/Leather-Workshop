package com.leather.workshop.global.config.security.dto;

import com.leather.workshop.domain.login.domain.Role;
import com.leather.workshop.domain.login.domain.User;
import lombok.Getter;

@Getter
public class SessionUser {
    private String name;
    private String email;
    private String picture;
    private Role role;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.role = user.getRole();
    }
}
