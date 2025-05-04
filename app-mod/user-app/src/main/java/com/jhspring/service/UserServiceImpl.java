package com.jhspring.service;

import com.jhspring.common.util.BcryptUtil;
import com.jhspring.data.entity.User;
import com.jhspring.data.repository.UserRepository;
import com.jhspring.service.inf.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BcryptUtil bcryptUtil;

    @Override
    public void register(User user) {

    }

    @Override
    public User login(String username, String password) {
        return null;
    }

}