package com.jhspring.mapper;

import com.jhspring.core.user.LoginUser;
import com.jhspring.dto.req.LoginReqDto;
import com.jhspring.dto.res.LoginResDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginMapper {
    LoginReqDto toRequestDto(LoginUser loginUser);
    LoginResDto toResponseDto(LoginUser loginUser);
}
