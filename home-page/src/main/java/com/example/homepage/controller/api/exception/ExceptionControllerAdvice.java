package com.example.homepage.controller.api.exception;

import com.example.homepage.domain.dto.HttpResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler
    @ResponseBody
    public HttpResponseDto<?> exceptionHandler(Exception e, HttpServletRequest request) {
        log.error("[ExceptionControllerAdvice] exceptionHandler", e);
        return new HttpResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR, "에러 처리 외의 오류", null);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    private HttpResponseDto<?> IllegalArgumentExceptionHandler(IllegalArgumentException e, HttpServletRequest request) {
        log.error("[ExceptionControllerAdvice] IllegalArgumentExceptionHandler", e);
        return new HttpResponseDto<>(HttpStatus.BAD_REQUEST, "IllegalArgumentException 찾을 수 없음", null);
    }

}
