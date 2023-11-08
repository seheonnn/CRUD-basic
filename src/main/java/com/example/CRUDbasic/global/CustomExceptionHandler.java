package com.example.CRUDbasic.global;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(BaseException.class)
    protected BaseResponse<String> handleCustomException(BaseException exception) {
        return new BaseResponse<>(exception.getStatus());
    }
}
