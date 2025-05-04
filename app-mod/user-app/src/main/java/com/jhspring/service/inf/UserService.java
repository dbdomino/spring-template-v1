package com.jhspring.service.inf;

import com.jhspring.common.dto.ApiResponse;
import com.jhspring.dto.req.LoginReqDto;
import com.jhspring.dto.req.RegistUserReqDto;
import com.jhspring.dto.res.LoginResDto;
import com.jhspring.dto.res.RegistUserResDto;

public interface UserService {
    ApiResponse<RegistUserResDto> register(RegistUserReqDto request);
    ApiResponse<LoginResDto> login(LoginReqDto request);
}
