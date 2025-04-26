package com.jhspring.service;

import com.jhspring.data.entity.User;
import com.jhspring.data.repo.UserRepository;
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
