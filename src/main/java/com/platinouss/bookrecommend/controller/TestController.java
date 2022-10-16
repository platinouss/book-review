package com.platinouss.bookrecommend.controller;

import com.platinouss.bookrecommend.domain.Book;
import com.platinouss.bookrecommend.domain.Review;
import com.platinouss.bookrecommend.domain.User;
import com.platinouss.bookrecommend.dto.BookDto;
import com.platinouss.bookrecommend.service.BookService;
import com.platinouss.bookrecommend.service.ReviewService;
import com.platinouss.bookrecommend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final UserService userService;
    private final ReviewService reviewService;
    private final BookService bookService;

    @PostMapping("/user/add")
    public String addUser(@RequestBody User user) {
        userService.addUser(user);

        return "add user success";
    }

    @PostMapping("/review/add")
    public Review addReview(@RequestBody Review review) {
        return reviewService.insert(review);
    }

    @PostMapping("/book/add")
    public Book addBook(@RequestBody BookDto book) {
        return bookService.save(book);
    }
}