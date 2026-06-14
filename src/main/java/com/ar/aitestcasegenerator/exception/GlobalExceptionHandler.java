package com.ar.aitestcasegenerator.exception;

import com.ar.aitestcasegenerator.dto.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFound(
            ResourceNotFoundException exception,
            HttpServletRequest request) {
        log.warn("Resource not found for {}: {}", request.getRequestURI(), exception.getMessage());

        return buildErrorResponse(
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                null,
                request.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidation(
            MethodArgumentNotValidException exception,
            HttpServletRequest request) {
        Map<String, String> errors = new LinkedHashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
                errors.putIfAbsent(error.getField(), error.getDefaultMessage()));

        log.warn("Validation failed for {}: {}", request.getRequestURI(), errors);

        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Request validation failed",
                errors,
                request.getRequestURI());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleUnreadableRequest(
            HttpMessageNotReadableException exception,
            HttpServletRequest request) {
        log.warn("Malformed request body for {}", request.getRequestURI());

        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Request body is missing or malformed",
                null,
                request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnexpectedException(
            Exception exception,
            HttpServletRequest request) {
        log.error("Unexpected error while processing {}", request.getRequestURI(), exception);

        return buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred",
                null,
                request.getRequestURI());
    }

    private ResponseEntity<ApiResponse<Void>> buildErrorResponse(
            HttpStatus status,
            String message,
            Map<String, String> errors,
            String path) {
        ApiResponse<Void> response = ApiResponse.failure(
                status.value(),
                message,
                errors,
                path);
        return ResponseEntity.status(status).body(response);
    }
}
