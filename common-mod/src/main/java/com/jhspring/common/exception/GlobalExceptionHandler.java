package com.jhspring.common.exception;

import com.jhspring.common.constants.ErrorCode;
import com.jhspring.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 커스텀 예외 처리 (CoException)
    @ExceptionHandler(CoException.class)
    public ResponseEntity<ApiResponse<Void>> handleCoException(CoException ex) {
        if (ex.getErrorCode() != null) {
            return ResponseEntity.status(ex.getStatusCode())
                    .body(ApiResponse.fail(ex.getErrorCode()));
        } else {
            try {
                ErrorCode errorCode = ErrorCode.valueOf(ex.getMessage());
                return ResponseEntity.status(errorCode.getStatus())
                        .body(ApiResponse.fail(errorCode));
            } catch (IllegalArgumentException e) {
                // 메시지가 ErrorCode name과 일치하지 않는 경우 fallback
                return ResponseEntity.status(ex.getStatusCode())
                        .body(ApiResponse.fail(ex.getMessage()));
            }
        }
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
    public ResponseEntity<ApiResponse<Void>> handleRuntime(RuntimeException ex) {
        return ResponseEntity
                .internalServerError()
                .body(ApiResponse.fail(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    // 예외 메시지와 함께 반환하고 싶을 때
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGeneric(Exception ex) {
        return ResponseEntity
                .status(500)
                .body(ApiResponse.fail(ErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage()));
    }
}
