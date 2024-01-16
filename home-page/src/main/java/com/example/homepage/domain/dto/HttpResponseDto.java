package com.example.homepage.domain.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class HttpResponseDto<T> {

    private HttpStatus code;
    private String message;
    private T data;

    public HttpResponseDto(T data) {
        this.code = HttpStatus.OK;
        this.data = data;
    }

    public HttpResponseDto(HttpStatus code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
