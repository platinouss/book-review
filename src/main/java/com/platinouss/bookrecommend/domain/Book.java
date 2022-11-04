package com.platinouss.bookrecommend.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    private String name;

    private String category;

    private String imageLink;

    private long isbn;

    private String pubdate;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bood_review_info_id")
    private BookReviewInfo bookReviewInfo;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;
}
