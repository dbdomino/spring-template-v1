package com.jhspring.front.controller;

import com.jhspring.front.service.itf.UserPageService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class LogoutController {

    private final UserPageService userPageService;

    @PostMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirect) {
        userPageService.logout(session);
        redirect.addFlashAttribute("message", "로그아웃 되었습니다.");

        return "redirect:/login";
    }
}
