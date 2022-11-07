package com.platinouss.bookrecommend.service;

import com.platinouss.bookrecommend.domain.Book;
import com.platinouss.bookrecommend.domain.Review;
import com.platinouss.bookrecommend.domain.User;
import com.platinouss.bookrecommend.domain.enums.Gender;
import com.platinouss.bookrecommend.naver.dto.NaverBookDto;
import com.platinouss.bookrecommend.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReviewServiceTest {

    @Autowired
    private SearchBookService searchBookService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @BeforeEach
    void before() throws IOException {
        User user = User.builder()
                .name("platinouss")
                .password("1234")
                .email("platinouss@gmail.com")
                .gender(Gender.MALE)
                .reviews(new ArrayList<>())
                .build();
        userService.addUser(user);

        reviewRepository.deleteAll();

        List<NaverBookDto> bookDto = searchBookService.search("잘될 수밖에 없는 너에게");
        for(NaverBookDto book : bookDto) {
            bookService.add(book);
        }
    }

    @DisplayName("1. 리뷰 작성 후 조회")
    @Test
    @Transactional
    void test1() {
        User user = userService.find("platinouss@gmail.com");
        Book book = bookService.find("잘될 수밖에 없는 너에게");
        Review review = Review.builder()
                .id(book.getId())
                .title("너무 설명이 잘 되어있어요 !")
                .content("덕분에 많은 도움 됐습니다 ~~")
                .score(5)
                .book(book)
                .build();

        System.out.println("==");
        System.out.println(review.getContent());

        reviewService.add(book.getId(), user.getEmail(), review);

        List<Review> reviews = book.getReviews();
        assertEquals(1, reviews.size());

        List<Review> reviews1 = reviewRepository.findByTitle("너무 설명이 잘 되어있어요 !");
        Book book2 = reviews1.get(0).getBook();
        assertEquals("잘될 수밖에 없는 너에게", book2.getName());
    }
}
