package com.platinouss.bookrecommend.service;

import com.platinouss.bookrecommend.domain.Book;
import com.platinouss.bookrecommend.domain.Review;
import com.platinouss.bookrecommend.naver.NaverClient;
import com.platinouss.bookrecommend.repository.BookRepository;
import com.platinouss.bookrecommend.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;

    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    public Review insert(Review review) {
        return reviewRepository.save(review);
    }
}
