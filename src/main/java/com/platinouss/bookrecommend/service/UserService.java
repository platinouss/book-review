package com.platinouss.bookrecommend.service;

import com.platinouss.bookrecommend.domain.Author;
import com.platinouss.bookrecommend.domain.Authority;
import com.platinouss.bookrecommend.domain.User;
import com.platinouss.bookrecommend.repository.AuthorityRepository;
import com.platinouss.bookrecommend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        Authority a = Authority.builder()
                        .user(user)
                        .authority("ROLE_MANAGER")
                        .build();
        authorityRepository.save(a);

        user.setAuthorities(authorityRepository.findAuthorityByUser(user));

        userRepository.save(user);
    }
}
