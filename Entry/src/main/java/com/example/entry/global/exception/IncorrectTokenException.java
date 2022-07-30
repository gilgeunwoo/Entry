package com.example.entry.global.exception;

import com.example.entry.domain.user.exception.InvalidPasswordException;
import com.example.entry.global.error.ErrorCode;

public class IncorrectTokenException extends CustomException {

    public static final CustomException EXCEPTION =
            new IncorrectTokenException();

    private IncorrectTokenException() {
        super(ErrorCode.INCORRECT_TOKEN);
    }
}
