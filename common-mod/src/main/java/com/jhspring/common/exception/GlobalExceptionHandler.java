package com.jhspring.common.exception;

import com.jhspring.common.constants.ErrorCode;
import com.jhspring.common.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 커스텀 예외 처리 (CoException)
    @ExceptionHandler(CoException.class)
    public ResponseEntity<ApiResponse<Void>> handleCoException(CoException ex) {
        ErrorCode errorCode = ex.getErrorCode();

        HttpStatus httpStatus = switch (errorCode) {
            case USER_NOT_FOUND, DUPLICATE_ID, PASSWORD_MATCH_FAIL, LOGIN_FAIL -> HttpStatus.BAD_REQUEST;
            case INVALID_AUTH -> HttpStatus.UNAUTHORIZED;
            case FORBIDDEN -> HttpStatus.FORBIDDEN;
            case INTERNAL_SERVER_ERROR -> HttpStatus.INTERNAL_SERVER_ERROR;
            default -> HttpStatus.BAD_REQUEST;
        };

        return ResponseEntity
                .status(httpStatus)
                .body(ApiResponse.fail(errorCode));
    }
    // 잘못된 인자 예외
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.fail(ErrorCode.ETC_ERROR));
    }

    // 그 외 모든 RuntimeException
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<String>> handleRuntime(RuntimeException ex) {
        return ResponseEntity
                .internalServerError()
                .body(ApiResponse.fail(ErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage()));
    }

    // 예외 메시지와 함께 반환하고 싶을 때
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGeneric(Exception ex) {
        return ResponseEntity
                .status(500)
                .body(ApiResponse.fail(ErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage()));
    }
}
