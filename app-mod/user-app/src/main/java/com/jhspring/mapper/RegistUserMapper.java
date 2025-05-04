package com.jhspring.mapper;

import com.jhspring.core.user.User;
import com.jhspring.dto.res.RegistUserResDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RegistUserMapper {
    RegistUserMapper INSTANCE = Mappers.getMapper(RegistUserMapper.class);

    RegistUserResDto toRequestDto(User user);
    RegistUserResDto toResponseDto(User user);
}
