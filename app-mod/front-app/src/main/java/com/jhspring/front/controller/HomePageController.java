package com.jhspring.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {
    // 홈 페이지 (비로그인 기본 화면)
    @GetMapping("/")
    public String home() {
        return "user/home";
    }
}
