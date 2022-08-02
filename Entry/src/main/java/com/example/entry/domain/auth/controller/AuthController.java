package com.example.entry.domain.auth.controller;

import com.example.entry.domain.auth.controller.dto.request.SignInRequest;
import com.example.entry.domain.auth.controller.dto.request.SignUpRequest;
import com.example.entry.domain.auth.controller.dto.response.TokenResponse;
import com.example.entry.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public void signUp(@RequestBody SignUpRequest signUpRequest) {
        authService.signup(signUpRequest);
    }

    @PostMapping("/signin")
    public TokenResponse signin(@RequestBody SignInRequest signInRequest) {
        return authService.signIn(signInRequest);
    }
}
