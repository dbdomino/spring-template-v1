package com.jhspring.mapper;

import com.jhspring.data.entity.UserEntity;
import com.jhspring.dto.req.RegistUserReqDto;
import com.jhspring.dto.res.FindmeResDto;
import com.jhspring.dto.res.LoginResDto;
import com.jhspring.dto.res.RegistUserResDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    UserEntity toUserEntity(RegistUserReqDto dto);
    RegistUserResDto toRegisterDto(UserEntity entity);

    LoginResDto toLoginDto(UserEntity entity);
    FindmeResDto toFindmeDto(UserEntity entity);
}
