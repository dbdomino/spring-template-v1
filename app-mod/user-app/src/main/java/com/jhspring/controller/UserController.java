package com.jhspring.controller;

import com.jhspring.common.dto.ApiResponse;
import com.jhspring.dto.req.LoginReqDto;
import com.jhspring.dto.req.RegistUserReqDto;
import com.jhspring.dto.res.FindmeResDto;
import com.jhspring.dto.res.LoginResDto;
import com.jhspring.dto.res.RegistUserResDto;
import com.jhspring.service.inf.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegistUserResDto>> createUser(@Valid @RequestBody RegistUserReqDto dto) {
        return ResponseEntity.ok(userService.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResDto>> login(@RequestBody LoginReqDto dto, HttpSession session) {
        return ResponseEntity.ok(userService.login(dto, session));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(ApiResponse.success("로그아웃 완료", null));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<FindmeResDto>> me(HttpSession session) {
        String userId = (String) session.getAttribute("loginUser");
        return ResponseEntity.ok(userService.findMe(userId));
    }

}
