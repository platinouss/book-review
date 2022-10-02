package com.platinouss.bookrecommend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private String title;
    private String category;
    private String author;
    private String publisher;
    private String image;
    private long isbn;
    private String pubdate;
    private String link;
    private int discount;
    private String description;
}

