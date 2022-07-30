package com.example.entry.global.exception;

import com.example.entry.domain.user.exception.InvalidPasswordException;
import com.example.entry.global.error.ErrorCode;

public class ExpiredRefreshTokenException extends CustomException {
    public static final CustomException EXCEPTION =
            new ExpiredRefreshTokenException();

    private ExpiredRefreshTokenException() {
        super(ErrorCode.EXPIRED_REFRESH_TOKEN);
    }
}
