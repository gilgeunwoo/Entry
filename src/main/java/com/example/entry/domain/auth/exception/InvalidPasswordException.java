package com.example.entry.domain.auth.exception;

import com.example.entry.global.exception.CustomException;
import com.example.entry.global.error.ErrorCode;

public class InvalidPasswordException extends CustomException {

    public static final CustomException EXCEPTION =
            new InvalidPasswordException();

    private InvalidPasswordException() {
        super(ErrorCode.USER_EXISTS);
    }
}
