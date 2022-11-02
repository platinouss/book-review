package com.platinouss.bookrecommend.controller;

import com.platinouss.bookrecommend.service.BookService;
import com.platinouss.bookrecommend.service.ReviewService;
import com.platinouss.bookrecommend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyPageController {

    private final UserService userService;
    private final BookService bookService;
    private final ReviewService reviewService;

    @GetMapping("/api/mypage")
    public String main(Authentication authentication) {
        return "test";
    }
}
