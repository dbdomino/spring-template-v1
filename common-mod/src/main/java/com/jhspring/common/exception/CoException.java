package com.jhspring.common.exception;

import com.jhspring.common.constants.ErrorCode;

public class CoException extends RuntimeException{
    private final int statusCode;
    private final ErrorCode errorCode;

    public CoException(int statusCode) { this.statusCode = statusCode; errorCode = null; }
    public CoException(int statusCode, String message) { super(message);  this.statusCode = statusCode; errorCode = null; }
    public CoException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.statusCode = errorCode.getStatus();
        this.errorCode = errorCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
