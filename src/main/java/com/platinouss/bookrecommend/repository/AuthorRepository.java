package com.platinouss.bookrecommend.repository;

import com.platinouss.bookrecommend.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
