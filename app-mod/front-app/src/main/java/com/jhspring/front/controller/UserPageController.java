package com.jhspring.front.controller;

import com.jhspring.common.constants.ErrorCode;
import com.jhspring.front.dto.req.LoginReqDto;
import com.jhspring.front.dto.res.LoginResDto;
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

    // 로그인 페이지
    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }
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

    // 로그인 처리
    @PostMapping("/login")
    public String loginProcess(LoginReqDto req, HttpSession session, RedirectAttributes redirect) {
        LoginResDto res = userPageService.login(req);
        if (res == null) {
            redirect.addFlashAttribute("code", ErrorCode.LOGIN_FAIL.getMessage());
            redirect.addFlashAttribute("message", ErrorCode.LOGIN_FAIL.getMessage());
            return "redirect:/login";
        }

        session.setAttribute("loginUser", res.getId());
        session.setAttribute("loginUserName", res.getName());
        session.setAttribute("loginUserEmail", res.getEmail());

        return "redirect:/dashboard";
    }

}
