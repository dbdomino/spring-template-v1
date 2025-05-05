package com.jhspring.front.service.itf;

import com.jhspring.front.dto.req.LoginReqDto;
import com.jhspring.front.dto.req.RegistUserReqDto;
import com.jhspring.front.dto.res.FindmeResDto;
import com.jhspring.front.dto.res.LoginResDto;
import com.jhspring.front.dto.res.RegistUserResDto;
import jakarta.servlet.http.HttpSession;

public interface UserPageService {
    LoginResDto login(LoginReqDto reqDto, HttpSession session);
    RegistUserResDto register(RegistUserReqDto reqDto);
    FindmeResDto me(HttpSession session);
}
