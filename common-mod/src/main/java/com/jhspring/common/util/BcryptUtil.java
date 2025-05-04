package com.jhspring.common.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BcryptUtil {
    private final PasswordEncoder passwordEncoder;
    //비밀번호 암호화
    public String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    //비밀번호 비교
    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
