package com.platinouss.bookrecommend.service;

import com.platinouss.bookrecommend.domain.Book;
import com.platinouss.bookrecommend.naver.dto.NaverBookDto;
import com.platinouss.bookrecommend.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private SearchBookService searchBookService;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void before() throws IOException {
        bookRepository.deleteAll();

        List<NaverBookDto> bookDto1 = searchBookService.search("원씽(The One Thing) (복잡한 세상을 이기는 단순함의 힘)");
        Book book1 = bookService.add(bookDto1.get(0));
        log.info(book1.getName());

        List<NaverBookDto> bookDto2 = searchBookService.search("반짝이는 하루, 그게 오늘이야 (무료한 일상을 특별하게 바꿔줄 다이어리 북)");
        Book book2 = bookService.add(bookDto2.get(0));
        log.info(book2.getName());
    }

    @Test
    @DisplayName("1. 책 추가 후 조회")
    void test1() throws IOException {
        bookRepository.deleteAll();

        List<NaverBookDto> bookDto =  searchBookService.search("철학은 어떻게 삶의 무기가 되는가 (불확실한 삶을 돌파하는 50가지 생각 도구)");
        bookService.add(bookDto.get(0));

        Book book = bookService.find(3L);

        assertEquals("철학은 어떻게 삶의 무기가 되는가 (불확실한 삶을 돌파하는 50가지 생각 도구)", book.getName());
    }

    @Test
    @DisplayName("2. 이미 존재하는 책 조회")
    void test2() {
        List<Book> books = bookService.getAll();

        assertEquals("반짝이는 하루, 그게 오늘이야 (무료한 일상을 특별하게 바꿔줄 다이어리 북)", books.get(1).getName());
    }
}
