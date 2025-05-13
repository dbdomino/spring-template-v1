package com.jhspring.data.coreimpl;

import com.jhspring.core.user.User;
import com.jhspring.data.entity.UserEntity;

public class UserModel implements User {
    private final String id;
    private final String name;
    private final boolean status;
    private final String email;

    public UserModel(UserEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.status = entity.isStatus();
        this.email = entity.getEmail();
    }

    @Override public String getId() { return id; }
    @Override public String getName() { return name; }

    @Override public String getEmail()  { return email;  }
    @Override public boolean isStatus() { return status; }

}
