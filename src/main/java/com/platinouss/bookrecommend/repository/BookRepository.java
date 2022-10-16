package com.platinouss.bookrecommend.repository;

import com.platinouss.bookrecommend.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByName(String name);
    Optional<Book> findById(long id);
    Optional<Book> findBookByIsbn(long isbn);
}
