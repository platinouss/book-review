package com.platinouss.bookrecommend.service;

import com.platinouss.bookrecommend.domain.Book;
import com.platinouss.bookrecommend.dto.BookDto;
import com.platinouss.bookrecommend.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book save(BookDto bookDto) {
        Book book = Book.builder().name(bookDto.getTitle()).category(bookDto.getCategory()).imageLink(bookDto.getImage())
                .isbn(bookDto.getIsbn()).pubdate(bookDto.getPubdate()).description(bookDto.getDescription().substring(0, 50)).build();

        return bookRepository.save(book);
    }

    public Book find(String name) {
        return bookRepository.findByName(name);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
