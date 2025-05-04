package com.jhspring.common.constants;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // === 4xx Client Errors ===
    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "404 페이지 찾을 수 없습니다."),
    DUPLICATE_ID(HttpStatus.CONFLICT.value(), "409 id중복"),
    INVALID_AUTH(HttpStatus.UNAUTHORIZED.value(), "401 인증실패"),
    FORBIDDEN(HttpStatus.FORBIDDEN.value(), "403 접근 권한이 없습니다."),
    // === 5xx Server Errors ===
    INTERNAL_SERVER_ERROR(500, "500 서버 내부 오류가 발생했습니다."),
    NOT_IMPLEMENTED(501, "501 아직 구현되지 않은 기능입니다."),
    BAD_GATEWAY(502, "502 잘못된 게이트웨이 응답입니다."),
    SERVICE_UNAVAILABLE(503, "503 서비스가 일시적으로 사용 불가능합니다."),
    // === 1xxx Auth ERROR ===
    AUTH_FAIL(1001, "인증실패"),
    // === 2xxx User ERROR ===
    LOGIN_FAIL(2001, "로그인 실패"),
    DUPLICATE_ID_REGIST_FAIL(2002, "회원가입 실패, 중복된 ID존재"),

    // === 9xxx etc ERROR ===
    ETC_ERROR(9999, "일반적인 실패");

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
