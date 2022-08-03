package com.example.entry.domain.user.controller.dto.response;

import com.example.entry.domain.submit.domain.Application;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {

    private String username;

    private String email;

    private String password;

    private Application application;
}
