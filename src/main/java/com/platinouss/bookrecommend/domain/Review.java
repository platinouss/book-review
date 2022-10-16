package com.platinouss.bookrecommend.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private float score;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @ToString.Exclude
    private User user;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    @ToString.Exclude
    private Book book;
}
