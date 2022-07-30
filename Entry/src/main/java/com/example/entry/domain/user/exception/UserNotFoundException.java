package com.example.entry.domain.user.exception;

import com.example.entry.global.exception.CustomException;
import com.example.entry.global.error.ErrorCode;

public class UserNotFoundException extends CustomException {

    public static final CustomException EXCEPTION =
            new UserNotFoundException();

    private UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
