package com.example.entry.domain.submit.exception;

import com.example.entry.domain.user.exception.UserNotFoundException;
import com.example.entry.global.error.ErrorCode;
import com.example.entry.global.exception.CustomException;

public class ApplicationNotFoundException extends CustomException {

    public static final CustomException EXCEPTION =
            new ApplicationNotFoundException();

    private ApplicationNotFoundException() {
        super(ErrorCode.APPLICATION_NOT_FOUND);
    }
}
