package com.example.entry.global.error;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    USER_NOT_FOUND(404, "user not found"),
    INVALID_PASSWORD(401, "invalid password"),
    EXPIRED_REFRESH_TOKEN(401,"Expired Refresh Token"),
    INVALID_TOKEN(401,"Invalid Token"),
    INCORRECT_TOKEN(500,"Incorrect Token"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int status;
    private final String message;
}