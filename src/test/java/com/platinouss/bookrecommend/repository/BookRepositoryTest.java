package com.platinouss.bookrecommend.repository;

import com.platinouss.bookrecommend.domain.*;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookAndAuthorRepository bookAndAuthorRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void crudTest() {
        Book book = new Book();
        book.setName("잘될 수밖에 없는 너에게");

        bookRepository.save(book);

        System.out.println(bookRepository.findAll());
    }

    @Test
    void setPublisherTest() {
        Publisher publisher = publisherRepository.findByName("북로망스");

        Book book = new Book();

        book.setName("잘될 수밖에 없는 너에게");
        book.setCategory("에세이");
        book.setPublisher(publisher);

        bookRepository.save(book);
        Book book1 = bookRepository.findByName("잘될 수밖에 없는 너에게");
        System.out.println(">> 출판사 : " + book1.getPublisher().getName());
    }

    @Test
    void setReviewTest() {
        setPublisherTest();

        Book book = bookRepository.findByName("잘될 수밖에 없는 너에게");

        Review review = new Review();
        review.setTitle("깊이 있는 학습을 할 수 있는 책");
        review.setContent("이 책을 통해 Spring Data JPA의 구동 방식에 대해 배울 수 있었어요 !");
        review.setScore(5.0f);
        review.setBook(book);

        reviewRepository.save(review);

        List<Review> reviews = reviewRepository.findByBookName("잘될 수밖에 없는 너에게");
        System.out.println(reviews);
    }

    @Test
    void setAuthorTest() {
        setReviewTest();

        Book book = bookRepository.findByName("잘될 수밖에 없는 너에게");

        Author author = new Author();
        author.setName("platinouss");
        authorRepository.save(author);

        BookAndAuthor bookAndAuthor = new BookAndAuthor();
        bookAndAuthor.setBook(book);
        bookAndAuthor.setAuthor(author);

        bookAndAuthorRepository.save(bookAndAuthor);

        System.out.println(bookAndAuthorRepository.findAll());
    }
}