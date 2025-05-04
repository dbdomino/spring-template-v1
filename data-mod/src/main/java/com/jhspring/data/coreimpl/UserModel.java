package com.jhspring.data.coreimpl;

import com.jhspring.core.user.User;

public class UserModel implements User {
    private final String id;
    private final String name;
    private final boolean status;
    private final String email;

    public UserModel(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.status = user.isStatus();
        this.email = user.getEmail();
    }

    @Override public String getId() { return id; }
    @Override public String getName() { return name; }

    @Override public String getEmail()  { return email;  }
    @Override public boolean isStatus() { return status; }

}
