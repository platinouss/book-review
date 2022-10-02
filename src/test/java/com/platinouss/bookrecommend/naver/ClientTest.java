package com.platinouss.bookrecommend.naver;

import com.platinouss.bookrecommend.naver.dto.SearchBookReq;
import com.platinouss.bookrecommend.naver.dto.SearchBookRes;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
public class ClientTest {

    @Autowired
    private NaverClient naverClient;

    @Test
    @DisplayName("1. 네이버 API에 검색어 질의")
    public void searchBookTest() {
        var search = new SearchBookReq();
        search.setQuery("잘될 수밖에 없는 너에게");

        SearchBookRes result = naverClient.bookSearch(search);

        assertEquals("잘될 수밖에 없는 너에게", result.getItems().get(0).getTitle());
        assertEquals("최서영", result.getItems().get(0).getAuthor());
        assertEquals("북로망스", result.getItems().get(0).getPublisher());
        assertEquals(9791191891201L, result.getItems().get(0).getIsbn());
    }
}
