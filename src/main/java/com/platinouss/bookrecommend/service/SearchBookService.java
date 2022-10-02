package com.platinouss.bookrecommend.service;

import com.platinouss.bookrecommend.domain.Book;
import com.platinouss.bookrecommend.dto.BookDto;
import com.platinouss.bookrecommend.naver.NaverClient;
import com.platinouss.bookrecommend.naver.dto.SearchBookReq;
import com.platinouss.bookrecommend.naver.dto.SearchBookRes;
import com.platinouss.bookrecommend.support.MyCrawler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SearchBookService {
    private final NaverClient naverClient;

    public BookDto search(String query) throws IOException {
        SearchBookReq searchBookReq = new SearchBookReq();
        searchBookReq.setQuery(query);

        SearchBookRes searchBookRes = naverClient.bookSearch(searchBookReq);

        BookDto result = new BookDto();
        if(searchBookRes.getTotal() > 0) {
            MyCrawler myCrawler = new MyCrawler();
            SearchBookRes.SearchBookItem bookItem = searchBookRes.getItems().get(0);

            result.setTitle(bookItem.getTitle());
            result.setCategory(myCrawler.categoryCrawler(bookItem.getLink()));
            result.setAuthor(bookItem.getAuthor());
            result.setPublisher(bookItem.getPublisher());
            result.setImage(bookItem.getImage());
            result.setIsbn(bookItem.getIsbn());
            result.setDescription(bookItem.getDescription());
            result.setPubdate(bookItem.getPubdate());
            result.setLink(bookItem.getLink());
            result.setDiscount(bookItem.getDiscount());

            return result;
        }

        return new BookDto();
    }
}
