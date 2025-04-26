package com.jhspring.core.user;

import com.jhspring.data.entity.User;

public class LoginUserImpl implements LoginUser{
    private final String id;
    private final String username;
    private final String password;
    private final boolean active;

    public LoginUserImpl(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.active = user.isActive();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
