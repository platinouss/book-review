package com.platinouss.bookrecommend.controller;

import com.platinouss.bookrecommend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping("/")
    public String main(Authentication authentication, Model m) {
        if(authentication != null) {
            m.addAttribute("username", authentication.getName());
        }

        return "main.html";
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }
}
