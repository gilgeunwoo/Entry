package com.example.entry.global.exception;

import com.example.entry.global.error.ErrorCode;

public class InvalidTokenException extends CustomException {
    public static final CustomException EXCEPTION =
            new InvalidTokenException();

    private InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
