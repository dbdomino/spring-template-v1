package com.jhspring.user.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginReqDto {
    private String id;
    private String password;
}
