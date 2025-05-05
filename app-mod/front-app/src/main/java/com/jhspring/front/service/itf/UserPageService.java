package com.jhspring.front.service.itf;

import com.jhspring.front.dto.req.LoginReqDto;
import com.jhspring.front.dto.res.LoginResDto;

public interface UserPageService {
    public LoginResDto login(LoginReqDto reqDto);
}
