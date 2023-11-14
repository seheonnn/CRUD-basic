package com.example.CRUDbasic.global;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 전역적으로 예외를 처리하는 컨트롤러 어드바이스
public class CustomExceptionHandler {
    @ExceptionHandler(BaseException.class) // BaseException 클래스에 대한 예외 처리
    protected BaseResponse<String> handleCustomException(BaseException exception) {
        return new BaseResponse<>(exception.getStatus());
    }
}
