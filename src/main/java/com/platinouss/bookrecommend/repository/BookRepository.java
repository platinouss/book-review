package com.platinouss.bookrecommend.repository;

import com.platinouss.bookrecommend.domain.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b" +
            " left join fetch b.bookReviewInfo" +
            " left join fetch b.publisher")
    List<Book> findAll();

    @Query("select b from Book b" +
            " left join fetch b.bookReviewInfo br" +
            " left join fetch b.publisher p" +
            " where b.name = :name")
    Optional<Book> findByName(@Param("name") String name);

    @Query("select b from Book b" +
            " left join fetch b.bookReviewInfo br" +
            " left join fetch b.publisher p" +
            " where b.id = :id")
    Optional<Book> findById(@Param("id") Long id);

    Optional<Book> findBookByIsbn(Long isbn);
}
