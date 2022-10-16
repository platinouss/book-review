package com.platinouss.bookrecommend.service;

import com.platinouss.bookrecommend.domain.Author;
import com.platinouss.bookrecommend.domain.Book;
import com.platinouss.bookrecommend.domain.BookAndAuthor;
import com.platinouss.bookrecommend.repository.BookAndAuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookAndAuthorService {

    private final BookAndAuthorRepository bookAndAuthorRepository;

    public BookAndAuthor add(Book book, Author author) {
        return bookAndAuthorRepository.save(
                BookAndAuthor.builder()
                        .book(book)
                        .author(author)
                        .build()
        );
    }

}
