package com.ar.aitestcasegenerator.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private final LocalDateTime timestamp;
    private final int status;
    private final boolean success;
    private final String message;
    private final T data;
    private final Map<String, String> errors;
    private final String path;

    private ApiResponse(
            int status,
            boolean success,
            String message,
            T data,
            Map<String, String> errors,
            String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.success = success;
        this.message = message;
        this.data = data;
        this.errors = errors;
        this.path = path;
    }

    public static <T> ApiResponse<T> success(int status, String message, T data) {
        return new ApiResponse<>(status, true, message, data, null, null);
    }

    public static ApiResponse<Void> failure(
            int status,
            String message,
            Map<String, String> errors,
            String path) {
        return new ApiResponse<>(status, false, message, null, errors, path);
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public String getPath() {
        return path;
    }
}
