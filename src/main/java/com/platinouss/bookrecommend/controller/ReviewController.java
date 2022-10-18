package com.platinouss.bookrecommend.controller;

import com.platinouss.bookrecommend.domain.Book;
import com.platinouss.bookrecommend.domain.Review;
import com.platinouss.bookrecommend.domain.User;
import com.platinouss.bookrecommend.repository.BookRepository;
import com.platinouss.bookrecommend.service.BookService;
import com.platinouss.bookrecommend.service.ReviewService;
import com.platinouss.bookrecommend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final BookService bookService;
    private final UserService userService;

    @GetMapping("review/{book_id}")
    public String add(@PathVariable Long book_id, Model m) {
        m.addAttribute("book_id", book_id);
        m.addAttribute("review", new Review());

        return "review.html";
    }

    @PostMapping("review/{book_id}")
    public String add(@PathVariable Long book_id, @ModelAttribute Review review,
                      Authentication authentication, Model m) {

        reviewService.add(book_id, authentication.getName(), review);

        return "redirect:/";
    }
}
