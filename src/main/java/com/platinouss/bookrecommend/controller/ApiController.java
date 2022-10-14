package com.platinouss.bookrecommend.controller;

import com.platinouss.bookrecommend.domain.User;
import com.platinouss.bookrecommend.dto.BookDto;
import com.platinouss.bookrecommend.service.SearchBookService;
import com.platinouss.bookrecommend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class ApiController {

    private final SearchBookService searchBookService;

    @GetMapping("/search")
    public BookDto search(@RequestParam String query) throws IOException {
        return searchBookService.search(query);
    }
}
