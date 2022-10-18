package com.platinouss.bookrecommend.service;

import com.platinouss.bookrecommend.domain.Book;
import com.platinouss.bookrecommend.domain.Review;
import com.platinouss.bookrecommend.domain.User;
import com.platinouss.bookrecommend.naver.NaverClient;
import com.platinouss.bookrecommend.repository.BookRepository;
import com.platinouss.bookrecommend.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookService bookService;
    private final UserService userService;

    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    public Review add(Long book_id, String username, Review review) {
        Book book = bookService.find(book_id);
        User user = userService.find(username);

        review.setUser(user);
        review.setBook(book);

        user.getReviews().add(review);
        book.getReviews().add(review);

        return reviewRepository.save(review);
    }
}
