package com.platinouss.bookrecommend.repository;

import com.platinouss.bookrecommend.domain.BookAndAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookAndAuthorRepository extends JpaRepository<BookAndAuthor, Long> {
}
