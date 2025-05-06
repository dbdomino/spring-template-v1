package com.jhspring.front.dto.req;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LoginReqDto  {
    private String id;
    private String password;
}
