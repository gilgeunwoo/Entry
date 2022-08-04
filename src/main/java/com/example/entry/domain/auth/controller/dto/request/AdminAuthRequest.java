package com.example.entry.domain.auth.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AdminAuthRequest {

    private String adminId;

    private String password;
}
