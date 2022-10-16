package com.platinouss.bookrecommend.repository;

import com.platinouss.bookrecommend.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findAuthorByName(String name);
    Optional<Author> findAuthorById(Long id);
}
