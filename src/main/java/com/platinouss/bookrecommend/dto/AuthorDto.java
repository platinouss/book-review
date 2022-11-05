package com.platinouss.bookrecommend.dto;

import com.platinouss.bookrecommend.domain.Author;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class AuthorDto {
    private String name;
    private List<BookDto> books;

    public AuthorDto(Author author) {
        this.name = author.getName();
        this.books = author.getBookAndAuthors().stream()
                .map(bookAndAuthor -> new BookDto(bookAndAuthor.getBook()))
                .collect(Collectors.toList());
    }
}
