package com.platinouss.bookrecommend.api;

import com.platinouss.bookrecommend.domain.Book;
import com.platinouss.bookrecommend.dto.BookDto;
import com.platinouss.bookrecommend.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MainPageApiController {

    private final BookService bookService;

    @GetMapping("/api/main")
    public List<BookDto> mypage() {
        List<Book> books = bookService.getAll();

        return books.stream()
                .map(book -> new BookDto(book))
                .collect(Collectors.toList());
    }
}
