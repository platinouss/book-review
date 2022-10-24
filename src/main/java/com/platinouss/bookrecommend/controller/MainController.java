package com.platinouss.bookrecommend.controller;

import com.platinouss.bookrecommend.domain.User;
import com.platinouss.bookrecommend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/register")
    public String register(@ModelAttribute User user, Model m) {
        m.addAttribute("user", user);

        return "register.html";
    }

    @PostMapping("/register")
    public String register(User user) {
        if(!userService.exists(user.getName())) {
            userService.addUser(user);
        }

        return "redirect:/";
    }
}
