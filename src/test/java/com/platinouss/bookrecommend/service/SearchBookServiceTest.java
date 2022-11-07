package com.platinouss.bookrecommend.service;

import com.platinouss.bookrecommend.naver.dto.NaverBookDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SearchBookServiceTest {

    @Autowired
    private SearchBookService searchBookService;

    @Test
    @DisplayName("1. 정상적인 책 1권만 검색")
    void tes1() throws IOException {
        List<NaverBookDto> bookDto =  searchBookService.search("잘될 수밖에 없는 너에게");
        assertEquals(1, bookDto.size());

        assertEquals(9791191891201L, bookDto.get(0).getIsbn());
        assertEquals("시/에세이", bookDto.get(0).getCategory());
        assertEquals("북로망스", bookDto.get(0).getPublisher());
    }

    @Test
    @DisplayName("2. 광범위한 이름으로 여러 권 검색")
    void test2() throws IOException {
        List<NaverBookDto> bookDto =  searchBookService.search("철학");
        assertEquals(10, bookDto.size());
    }

    @Test
    @DisplayName("3. 비정상적인 이름으로 검색")
    void test3() throws IOException {
        List<NaverBookDto> bookDto =  searchBookService.search("^~!");
        assertEquals(0, bookDto.size());
    }
}