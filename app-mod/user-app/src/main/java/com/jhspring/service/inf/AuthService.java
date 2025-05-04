package com.jhspring.service.inf;

import com.jhspring.data.entity.User;

public interface AuthService {
    void register(User user);
    void login(String username, String password);
}
