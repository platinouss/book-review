package com.platinouss.bookrecommend.api;

import com.platinouss.bookrecommend.domain.Author;
import com.platinouss.bookrecommend.dto.AuthorDto;
import com.platinouss.bookrecommend.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/author")
public class AuthorApiController {

    private final AuthorService authorService;

    @GetMapping("/{id}")
    public AuthorDto detail(@PathVariable Long id) {
        Author author = authorService.find(id);

        return new AuthorDto(author);
    }
}
