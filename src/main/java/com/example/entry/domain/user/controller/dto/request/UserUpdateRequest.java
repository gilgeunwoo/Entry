package com.example.entry.domain.user.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserUpdateRequest {

    private String username;

    private String email;

    private String password;
}
