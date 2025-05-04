package com.jhspring.service;

import com.jhspring.common.dto.ApiResponse;
import com.jhspring.common.util.BcryptUtil;
import com.jhspring.data.repository.UserRepository;
import com.jhspring.dto.req.LoginReqDto;
import com.jhspring.dto.req.RegistUserReqDto;
import com.jhspring.dto.res.LoginResDto;
import com.jhspring.dto.res.RegistUserResDto;
import com.jhspring.service.inf.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BcryptUtil bcryptUtil;

    @Override
    public ApiResponse<RegistUserResDto> register(RegistUserReqDto request) {
        return null;
    }

    @Override
    public ApiResponse<LoginResDto> login(LoginReqDto request) {
        return null;
    }
}