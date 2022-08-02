package com.example.entry.domain.auth.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignInRequest {

    private String email;
    private String password;
}
