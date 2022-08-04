package com.example.entry.domain.auth.exception;

import com.example.entry.global.error.ErrorCode;
import com.example.entry.global.exception.CustomException;

public class AlreadyExistUserException extends CustomException {

    public static final CustomException EXCEPTION =
            new AlreadyExistUserException();

    private AlreadyExistUserException() {
        super(ErrorCode.USER_EXISTS);
    }
}
