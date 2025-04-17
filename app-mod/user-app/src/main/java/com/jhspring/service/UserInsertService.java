package com.jhspring.service;

import com.jhspring.entity.User;
import com.jhspring.repo.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInsertService {
    private final UserRepository userRepository;

    @Transactional
    public void insert(User user) {
        userRepository.save(user);
    }
}
