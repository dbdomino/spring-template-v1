package com.jhspring.front.controller;

import com.jhspring.front.dto.req.RegistUserReqDto;
import com.jhspring.front.dto.res.RegistUserResDto;
import com.jhspring.front.service.itf.UserPageService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class UserPageController {
    private final UserPageService userPageService;

    // 회원가입 페이지
    @GetMapping("/signup")
    public String signupPage() {
        return "user/signup";
    }

    // 대시보드
    @GetMapping("/dashboard")
    public String dashboardPage(HttpSession session, Model model) {
        Object loginUser = session.getAttribute("loginUser");

        if (loginUser == null) {
            return "redirect:/login?message=로그인이 필요합니다.";
        }

        // 예: 세션에서 username 꺼내서 model에 넣기 (UserModel 구조에 맞게 수정)
        model.addAttribute("username", /* 예시: ((UserModel) loginUser).getUsername() */ "사용자");

        return "user/dashboard";
    }



    @PostMapping("/signup")
    public String signup(RegistUserReqDto req, RedirectAttributes redirect) {
        RegistUserResDto res = userPageService.register(req);
        redirect.addFlashAttribute("message", res.getMessage()); // "가입을 축하합니다" 등
        return "redirect:/login";
    }

}
