package com.platinouss.bookrecommend.api;

import com.platinouss.bookrecommend.domain.Book;
import com.platinouss.bookrecommend.dto.BookDto;
import com.platinouss.bookrecommend.naver.dto.NaverBookDto;
import com.platinouss.bookrecommend.service.BookService;
import com.platinouss.bookrecommend.service.SearchBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookApiController {

    private final BookService bookService;
    private final SearchBookService searchBookService;

    @GetMapping("/{id}")
    public BookDto detail(@PathVariable Long id) {
        Book book = bookService.find(id);

        return new BookDto(book);
    }

    @GetMapping("/search")
    public List<NaverBookDto> search(@RequestParam String query) throws IOException {
        return searchBookService.search(query);
    }
}
