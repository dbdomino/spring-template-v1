package com.jhspring.service.inf;

public interface AuthService {
    void sessionCreate(String username, String password);
    void jwtCreate(String username, String password);

}
