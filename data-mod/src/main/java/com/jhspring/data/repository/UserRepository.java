package com.jhspring.data.repository;

import com.jhspring.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<UserEntity, Long> {
    boolean existsById(String id); // Entity의 login ID 기준 존재 여부
    Optional<UserEntity> findById(String id); // user Id 로 조회하기.
}
