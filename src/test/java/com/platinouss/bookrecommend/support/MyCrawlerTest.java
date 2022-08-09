package com.platinouss.bookrecommend.support;

import com.platinouss.bookrecommend.naver.NaverClient;
import com.platinouss.bookrecommend.naver.dto.SearchBookReq;
import com.platinouss.bookrecommend.naver.dto.SearchBookRes;
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
    void categoryCrawlerTest() throws IOException {
        SearchBookReq search = new SearchBookReq();
        search.setQuery("운영체제");

        SearchBookRes result = naverClient.localSearch(search);

        MyCrawler myCrawler = new MyCrawler();
        String category = myCrawler.categoryCrawler(result.getItems().get(0).getLink());

        assertEquals(category, "컴퓨터/IT");
    }
}