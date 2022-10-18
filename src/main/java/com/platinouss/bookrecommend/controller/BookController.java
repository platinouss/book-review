package com.platinouss.bookrecommend.controller;

import com.platinouss.bookrecommend.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/{book_id}")
    public String detail(@PathVariable Long book_id, Model m) {
        m.addAttribute("book", bookService.find(book_id));

        return "book.html";
    }

}
