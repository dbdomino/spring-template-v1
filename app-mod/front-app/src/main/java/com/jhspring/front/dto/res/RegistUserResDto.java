package com.jhspring.front.dto.res;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistUserResDto {
    private Long idx;     // 사용자 PK
    private String id;    // 로그인 ID
    private String message;
}
