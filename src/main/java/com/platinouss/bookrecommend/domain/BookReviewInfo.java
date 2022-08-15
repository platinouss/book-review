package com.platinouss.bookrecommend.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BookReviewInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float averageReviewCount;

    private int reviewCount;

    @OneToOne(mappedBy = "bookReviewInfo")
    private Book book;
}
