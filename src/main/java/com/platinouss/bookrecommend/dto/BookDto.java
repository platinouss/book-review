package com.platinouss.bookrecommend.dto;

import com.platinouss.bookrecommend.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BookDto {
    private String title;
    private String category;
    private String author;
    private String publisher;
    private String image;
    private long isbn;
    private String pubdate;
    private String description;

    public BookDto(Book book) {
        this.title = book.getName();
        this.category = book.getCategory();
        this.author = book.getBookAndAuthors().get(0).getAuthor().getName();
        this.publisher = book.getPublisher().getName();
        this.image = book.getImageLink();
        this.isbn = book.getIsbn();
        this.pubdate = book.getPubdate();
        this.description = book.getDescription();
    }
}

