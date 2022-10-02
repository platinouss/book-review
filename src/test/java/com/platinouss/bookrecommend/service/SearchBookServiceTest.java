package com.platinouss.bookrecommend.service;

import com.platinouss.bookrecommend.dto.BookDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SearchBookServiceTest {

    @Autowired
    private SearchBookService searchBookService;

    @Test
    void searchBookServiceTest() throws IOException {
        BookDto bookDto =  searchBookService.search("잘될 수밖에 없는 너에게");
        System.out.println(bookDto);

        assertEquals(9791191891201L, bookDto.getIsbn());
        assertEquals("시/에세이", bookDto.getCategory());
        assertEquals("북로망스", bookDto.getPublisher());
    }
}