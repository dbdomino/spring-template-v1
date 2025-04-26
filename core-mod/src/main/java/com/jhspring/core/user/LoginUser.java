package com.jhspring.core.user;

public interface LoginUser {
    String getId();
    String getUsername();
    String getPassword();
    boolean isActive();

}
