package com.epps.epps.controller;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.OffsetDateTime;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Map<String, Object>> validation(MethodArgumentNotValidException exception) {
        Map<String, String> fields = exception.getBindingResult().getFieldErrors().stream()
            .collect(java.util.stream.Collectors.toMap(e -> e.getField(), e -> e.getDefaultMessage(), (first, ignored) -> first));
        return error(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR", fields);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<Map<String, Object>> badRequest(IllegalArgumentException exception) { return error(HttpStatus.BAD_REQUEST, exception.getMessage(), Map.of()); }
    private ResponseEntity<Map<String, Object>> error(HttpStatus status, String message, Map<String, String> fields) {
        return ResponseEntity.status(status).body(Map.of("timestamp", OffsetDateTime.now().toString(), "status", status.value(), "message", message, "fieldErrors", fields));
    }
}
