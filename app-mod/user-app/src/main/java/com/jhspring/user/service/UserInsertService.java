package com.jhspring.user.service;

import com.jhspring.data.entity.UserEntity;
import com.jhspring.data.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInsertService {
    private final UserRepository userRepository;

    @Transactional
    public void insert(UserEntity user) {
        userRepository.save(user);
    }
}
