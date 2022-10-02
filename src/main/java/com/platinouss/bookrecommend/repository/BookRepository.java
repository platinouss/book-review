package com.platinouss.bookrecommend.repository;

import com.platinouss.bookrecommend.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByName(String name);
    Book findById(long id);
}
