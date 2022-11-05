package com.platinouss.bookrecommend.service;

import com.platinouss.bookrecommend.domain.Author;
import com.platinouss.bookrecommend.repository.AuthorRepository;
import com.platinouss.bookrecommend.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Author add(String name) {
        Author author = authorRepository.findAuthorByName(name)
                .orElse(Author.builder()
                        .name(name)
                        .bookAndAuthors(new ArrayList<>())
                        .build()
                );

        return authorRepository.save(author);
    }

    public Author add(Author author) {
        return authorRepository.save(author);
    }

    public Author find(Long id) {
        return authorRepository.findAuthorById(id)
                .orElseThrow(RuntimeException::new);
    }
}
