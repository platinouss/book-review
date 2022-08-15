package com.platinouss.bookrecommend.repository;

import com.platinouss.bookrecommend.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByBookName(String name);
}
