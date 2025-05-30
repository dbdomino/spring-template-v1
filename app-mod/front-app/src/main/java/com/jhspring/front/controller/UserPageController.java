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



    // 대시보드
    @GetMapping("/dashboard")
    public String dashboardPage(HttpSession session, Model model) {
        Object loginId = session.getAttribute("loginId");

        if (loginId == null) {
            return "redirect:/login?message=로그인이 필요합니다.";
        }

        model.addAttribute("name",session.getAttribute("loginName"));

        return "dashboard";
    }

}
