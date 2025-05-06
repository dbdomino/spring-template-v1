package com.jhspring.user.service;

import com.jhspring.common.util.BcryptUtil;
import com.jhspring.data.repository.UserRepository;
import com.jhspring.user.service.inf.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final BcryptUtil bcryptUtil;

    @Override
    public void sessionCreate(String username, String password) {

    }

    @Override
    public void jwtCreate(String username, String password) {

    }
}
