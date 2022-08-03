package com.example.entry.domain.auth.controller;

import com.example.entry.domain.auth.controller.dto.request.AdminAuthRequest;
import com.example.entry.domain.auth.controller.dto.response.TokenResponse;
import com.example.entry.domain.auth.service.AdminAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth/admin")
public class AdminAuthController {

    private final AdminAuthService adminAuthService;

    @PostMapping("/signup")
    public void adminSignup(@RequestBody AdminAuthRequest adminAuthRequest) {
        adminAuthService.adminSignUp(adminAuthRequest);
    }

    @PostMapping("/signin")
    public TokenResponse adminSignin(@RequestBody AdminAuthRequest adminAuthRequest) {
        return adminAuthService.adminSignIn(adminAuthRequest);
    }
}
