package com.jhspring.data.mapper;

import com.jhspring.data.coreimpl.UserModel;
import com.jhspring.data.entity.UserEntity;
import org.mapstruct.Mapper;

// entity <-> model
@Mapper(componentModel = "spring") // → @Component 자동 등록됨
public interface UserEntityMapper {
    UserModel toModel(UserEntity entity);
    UserEntity toEntity(UserModel userModel);
}
