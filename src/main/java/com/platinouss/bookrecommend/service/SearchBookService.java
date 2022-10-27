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
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchBookService {
    private final NaverClient naverClient;

    public List<BookDto> search(String query) throws IOException {
        SearchBookReq searchBookReq = new SearchBookReq();
        List<BookDto> books = new ArrayList<>();
        try {
            searchBookReq.setQuery(query);
            SearchBookRes searchBookRes = naverClient.bookSearch(searchBookReq);

            if(searchBookRes.getTotal() > 0) {
                MyCrawler myCrawler = new MyCrawler();

                for (SearchBookRes.SearchBookItem bookItem : searchBookRes.getItems()) {
                    BookDto book = BookDto.builder()
                            .title(bookItem.getTitle())
                            .category(myCrawler.categoryCrawler(bookItem.getLink()))
                            .author(bookItem.getAuthor())
                            .publisher(bookItem.getPublisher())
                            .image(bookItem.getImage())
                            .isbn(bookItem.getIsbn())
                            .description(bookItem.getDescription())
                            .pubdate(bookItem.getPubdate())
                            .link(bookItem.getLink())
                            .discount(bookItem.getDiscount())
                            .build();

                    books.add(book);
                }
            }
        } catch(Exception ignored) {}

        return books;
    }
}
