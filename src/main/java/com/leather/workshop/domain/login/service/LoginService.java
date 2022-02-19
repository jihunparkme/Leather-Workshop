package com.leather.workshop.domain.login.service;

import com.leather.workshop.domain.login.domain.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Getter
@Service
public class LoginService {

    private final UserRepository userRepository;
}
