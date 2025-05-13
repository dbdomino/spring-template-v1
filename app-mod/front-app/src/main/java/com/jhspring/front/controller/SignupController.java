package com.jhspring.front.controller;

import com.jhspring.common.constants.ErrorCode;
import com.jhspring.front.dto.req.LoginReqDto;
import com.jhspring.front.dto.req.RegistUserReqDto;
import com.jhspring.front.dto.res.LoginResDto;
import com.jhspring.front.dto.res.RegistUserResDto;
import com.jhspring.front.service.itf.UserPageService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class SignupController {
    private final UserPageService userPageService;

    // 회원가입 페이지
    @GetMapping("/signup")
    public String signupPage() {
        return "user/signup";
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String signup(RegistUserReqDto req, RedirectAttributes redirect) {
        try {
            RegistUserResDto res = userPageService.register(req);
            redirect.addFlashAttribute("message", res.getMessage());  // 성공 메시지
            return "redirect:/login";
        } catch (RuntimeException e) {
            // 실패 메시지 → 다시 회원가입 폼으로
            redirect.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/signup";
        }
    }
}
