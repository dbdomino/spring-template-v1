package com.jhspring.front.service.itf;

import com.jhspring.front.dto.req.LoginReqDto;
import com.jhspring.front.dto.req.RegistUserReqDto;
import com.jhspring.front.dto.res.LoginResDto;
import com.jhspring.front.dto.res.RegistUserResDto;

public interface UserPageService {
    LoginResDto login(LoginReqDto reqDto);
    RegistUserResDto register(RegistUserReqDto reqDto);
}
