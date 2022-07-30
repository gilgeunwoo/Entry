package com.example.entry.domain.user.controller;

import com.example.entry.domain.user.controller.dto.request.SignInRequest;
import com.example.entry.domain.user.controller.dto.request.SignUpRequest;
import com.example.entry.domain.user.controller.dto.response.TokenResponse;
import com.example.entry.domain.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
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
