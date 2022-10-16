package com.platinouss.bookrecommend.service;

import com.platinouss.bookrecommend.domain.Publisher;
import com.platinouss.bookrecommend.dto.BookDto;
import com.platinouss.bookrecommend.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public Publisher add(String name) {
        Publisher publisher = publisherRepository.findByName(name)
                .orElse(Publisher.builder()
                        .name(name)
                        .books(new ArrayList<>())
                        .build()
                );

        return publisherRepository.save(publisher);
    }
}
