package com.platinouss.bookrecommend.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private String imageLink;

    private long isbn;

    private String pubdate;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "BOOK_REVIEW_INFO_ID")
    @ToString.Exclude
    private BookReviewInfo bookReviewInfo;

    @OneToMany(mappedBy = "book")
    @ToString.Exclude
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "PUBLISHER_ID")
    @ToString.Exclude
    private Publisher publisher;
}
