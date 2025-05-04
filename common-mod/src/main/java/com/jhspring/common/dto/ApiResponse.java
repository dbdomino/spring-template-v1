package com.jhspring.common.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.jhspring.common.constants.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonPropertyOrder({  "code", "message", "data" })
@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "요청 성공", data);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(200, message, data);
    }

    public static <T> ApiResponse<T> fail(ErrorCode errorCode) {
        return new ApiResponse<>(errorCode.getStatus(), errorCode.getMessage(), null);
    }
    public static <T> ApiResponse<T> fail(ErrorCode errorCode, T resultData) {
        return new ApiResponse<>(errorCode.getStatus(), errorCode.getMessage(), resultData);
    }
    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<>(500, message, null);
    }
}
