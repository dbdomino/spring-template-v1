package com.jhspring.user.service.inf;

import com.jhspring.common.dto.ApiResponse;
import com.jhspring.user.dto.req.LoginReqDto;
import com.jhspring.user.dto.req.RegistUserReqDto;
import com.jhspring.user.dto.res.FindmeResDto;
import com.jhspring.user.dto.res.LoginResDto;
import com.jhspring.user.dto.res.RegistUserResDto;
import jakarta.servlet.http.HttpSession;

public interface UserService {
    ApiResponse<RegistUserResDto> register(RegistUserReqDto request);
    ApiResponse<LoginResDto> login(LoginReqDto request, HttpSession session);
    ApiResponse<FindmeResDto> findMe(String userId);
}
