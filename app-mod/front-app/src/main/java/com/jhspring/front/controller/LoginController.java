package com.jhspring.front.controller;

import com.jhspring.common.constants.ErrorCode;
import com.jhspring.front.dto.req.LoginReqDto;
import com.jhspring.front.dto.res.LoginResDto;
import com.jhspring.front.service.itf.UserPageService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserPageService userPageService;

    // 로그인 페이지
    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String loginProcess(LoginReqDto req, HttpSession session, RedirectAttributes redirect) {
        LoginResDto res = userPageService.login(req, session);
        if (res == null) {
            redirect.addFlashAttribute("code", ErrorCode.LOGIN_FAIL.getMessage());
            redirect.addFlashAttribute("message", ErrorCode.LOGIN_FAIL.getMessage());
            return "redirect:/login";
        }

        session.setAttribute("loginId", res.getId());
        session.setAttribute("loginName", res.getName());

        return "redirect:/dashboard";
    }
}
