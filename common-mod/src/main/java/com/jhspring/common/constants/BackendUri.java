package com.jhspring.common.constants;

import lombok.Getter;

@Getter
public enum BackendUri {
    // === user-app uri ===
    LOGIN("POST","/api/v1/user/login"),
    LOGOUT("POST","/api/v1/user/logout"),
    REGISTER("POST","/api/v1/user/register"),
    ME("GET","/api/v1/user/me"),
    ETC( "POST","일반적인 실패");

    private final String type;
    private final String uri;

    BackendUri(String type, String uri) {
        this.type = type;
        this.uri = uri;
    }
}
