package com.platinouss.bookrecommend.naver;

import com.platinouss.bookrecommend.naver.dto.SearchBookReq;
import com.platinouss.bookrecommend.naver.dto.SearchBookRes;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ClientTest {

    @Autowired
    private NaverClient naverClient;

    @Test
    public void searchBookTest() {
        var search = new SearchBookReq();
        search.setQuery("JPA");

        SearchBookRes result = naverClient.bookSearch(search);

        System.out.println(result);
    }
}
