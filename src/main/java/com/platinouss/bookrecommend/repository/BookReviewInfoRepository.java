package com.platinouss.bookrecommend.repository;

import com.platinouss.bookrecommend.domain.BookReviewInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReviewInfoRepository extends JpaRepository<BookReviewInfo, Long> {
}
