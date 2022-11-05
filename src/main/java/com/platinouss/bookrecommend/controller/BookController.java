package com.platinouss.bookrecommend.controller;

import com.platinouss.bookrecommend.domain.Book;
import com.platinouss.bookrecommend.naver.dto.NaverBookDto;
import com.platinouss.bookrecommend.service.BookService;
import com.platinouss.bookrecommend.service.SearchBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final SearchBookService searchBookService;

    @GetMapping("/book/search")
    public String search(@RequestParam(value = "name", required = false) Optional<String> name, Model m) {
        if(name.isPresent()) {
            try {
                List<Book> books = new ArrayList<>();
                for(NaverBookDto book : searchBookService.search(name.get())) {
                    books.add(bookService.add(book));
                }
                m.addAttribute("books", books);
            } catch(Exception e) {
                m.addAttribute("error", "name_error");
            }
        }

        return "searchBook.html";
    }

    @GetMapping("/book/{book_id}")
    public String detail(@PathVariable Long book_id, Model m) {
        m.addAttribute("book", bookService.find(book_id));

        return "book.html";
    }


}
