package com.goorm.bakkuyoungapi.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Map<String, Object>> handleCustomException(ApplicationException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("code", ex.getErrorCode().name());
        body.put("message", ex.getMessage());
        return ResponseEntity
                .status(ex.getErrorCode().getStatus())
                .body(body);
    }

}
