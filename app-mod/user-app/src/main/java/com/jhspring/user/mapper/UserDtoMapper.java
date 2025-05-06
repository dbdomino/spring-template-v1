package com.jhspring.user.mapper;

import com.jhspring.data.entity.UserEntity;
import com.jhspring.user.dto.req.RegistUserReqDto;
import com.jhspring.user.dto.res.FindmeResDto;
import com.jhspring.user.dto.res.LoginResDto;
import com.jhspring.user.dto.res.RegistUserResDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    UserEntity toUserEntity(RegistUserReqDto dto);
    RegistUserResDto toRegisterDto(UserEntity entity);

    LoginResDto toLoginDto(UserEntity entity);
    FindmeResDto toFindmeDto(UserEntity entity);
}
