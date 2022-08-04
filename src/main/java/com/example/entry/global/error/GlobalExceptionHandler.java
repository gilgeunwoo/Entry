package com.example.entry.global.error;

import com.example.entry.global.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomErrorResponse> handleException(CustomException e) {
        final ErrorCode errorCode = e.getErrorCode();
        return new ResponseEntity<>(new CustomErrorResponse(errorCode.getMessage()), HttpStatus.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> handleValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(
                new CustomErrorResponse(e.getBindingResult().getAllErrors().get(0).getDefaultMessage()), HttpStatus.BAD_REQUEST);
    }
}
