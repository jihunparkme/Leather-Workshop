package com.leather.workshop.domain.login.web.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequest {

    @NotEmpty
    private String userId;

    @NotEmpty
    private String password;
}
