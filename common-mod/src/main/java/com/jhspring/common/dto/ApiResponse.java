package com.jhspring.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.jhspring.common.constants.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@JsonPropertyOrder({  "code", "message","timestamp", "data" })
@Data
@AllArgsConstructor
public class ApiResponse<T> {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime timestamp;

    private int code;
    private String message;
    private T data;

    // 성공 응답
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(LocalDateTime.now(), 200, "요청 성공", data);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(LocalDateTime.now(), 200, message, data);
    }

    // 실패 응답
    public static <T> ApiResponse<T> fail(ErrorCode errorCode) {
        return new ApiResponse<>(LocalDateTime.now(), errorCode.getStatus(), errorCode.getMessage(), null);
    }

    public static <T> ApiResponse<T> fail(ErrorCode errorCode, T resultData) {
        return new ApiResponse<>(LocalDateTime.now(), errorCode.getStatus(), errorCode.getMessage(), resultData);
    }

    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<>(LocalDateTime.now(), 500, message, null);
    }
}