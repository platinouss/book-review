package com.platinouss.bookrecommend.domain;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookReviewInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_review_info_id")
    private Long id;

    private float averageReviewCount;

    private int reviewCount;

    @OneToOne(mappedBy = "bookReviewInfo", fetch = FetchType.LAZY)
    private Book book;
}
