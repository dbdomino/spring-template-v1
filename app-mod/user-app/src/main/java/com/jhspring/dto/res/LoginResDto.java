package com.jhspring.dto.res;

import lombok.Getter;

@Getter
public class LoginResDto {
    private boolean success;
    private String id;
    private String name;

    public LoginResDto(boolean success, String id, String name) {
        this.success = success;
        this.id = id;
        this.name = name;
    }
}
