package com.jhspring.controller;

import com.jhspring.core.user.LoginUser;
import com.jhspring.data.entity.User;
import com.jhspring.data.servicie.LoginUserImpl;
import com.jhspring.service.inf.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String id,
                                   @RequestParam String password) {
        return ResponseEntity.ok("로그인 성공");
    }

}
