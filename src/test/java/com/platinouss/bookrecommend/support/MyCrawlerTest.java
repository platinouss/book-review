package com.platinouss.bookrecommend.support;

import com.platinouss.bookrecommend.naver.NaverClient;
import com.platinouss.bookrecommend.naver.dto.SearchBookReq;
import com.platinouss.bookrecommend.naver.dto.SearchBookRes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyCrawlerTest {

    @Autowired
    private NaverClient naverClient;

    @Test
    @DisplayName("1. Book 카테고리 값 가져오기")
    void categoryCrawlerTest() throws IOException {
        SearchBookReq search = new SearchBookReq();
        search.setQuery("잘될 수밖에 없는 너에게");

        SearchBookRes result = naverClient.bookSearch(search);

        MyCrawler myCrawler = new MyCrawler();
        String category = myCrawler.categoryCrawler(result.getItems().get(0).getLink());

        assertEquals(category, "시/에세이");
    }
}