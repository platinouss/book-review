package com.platinouss.bookrecommend.controller;

import com.platinouss.bookrecommend.service.BookService;
import com.platinouss.bookrecommend.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final BookService bookService;
    private final ReviewService reviewService;

    @GetMapping("/")
    public String main(Authentication authentication, Model model) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("books", bookService.getAll());
        model.addAttribute("reviews", reviewService.getAll());

        return "main.html";
    }
}
