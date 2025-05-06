package com.jhspring.user.mapper;

import com.jhspring.data.entity.UserEntity;
import com.jhspring.user.dto.res.FindmeResDto;
import com.jhspring.user.dto.res.LoginResDto;
import com.jhspring.user.dto.res.RegistUserResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    @Mapping(target = "message", ignore = true)
    RegistUserResDto toRegisterDto(UserEntity entity);

    @Mapping(target = "success", ignore = true)
    LoginResDto toLoginDto(UserEntity entity);

    FindmeResDto toFindmeDto(UserEntity entity);
}
