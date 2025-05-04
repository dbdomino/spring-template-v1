package com.jhspring.service;

import com.jhspring.data.entity.User;
import com.jhspring.data.repository.UserRepository;
import com.jhspring.service.inf.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    @Override
    public void register(User user) {

    }

    @Override
    public void login(String username, String password) {
//        return null;
    }
}
