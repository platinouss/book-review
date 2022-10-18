package com.platinouss.bookrecommend.controller;

import com.platinouss.bookrecommend.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/{author_id}")
    public String detail(@PathVariable Long author_id, Model m) {
        m.addAttribute("author", authorService.find(author_id));

        return "author.html";
    }
}
