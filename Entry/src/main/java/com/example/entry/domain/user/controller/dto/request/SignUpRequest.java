package com.example.entry.domain.user.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequest {

    private String username;    

    private String email;

    private String password;
}
