package com.jhspring.service.inf;

import com.jhspring.data.entity.User;

public interface UserService {
    void register(User user);
    User login(String username, String password);
}
