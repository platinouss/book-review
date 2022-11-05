package com.platinouss.bookrecommend.dto;

import com.platinouss.bookrecommend.domain.Review;
import lombok.Data;

@Data
public class ReviewDto {
    private String title;
    private String content;
    private float score;
    private String bookName;

    public ReviewDto(Review review) {
        this.title = review.getTitle();
        this.content = review.getContent();
        this.score = review.getScore();
        this.bookName = review.getBook().getName();
    }
}
