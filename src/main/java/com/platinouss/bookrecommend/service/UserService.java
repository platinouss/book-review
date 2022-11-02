package com.platinouss.bookrecommend.service;

import com.platinouss.bookrecommend.domain.Author;
import com.platinouss.bookrecommend.domain.Authority;
import com.platinouss.bookrecommend.domain.User;
import com.platinouss.bookrecommend.repository.AuthorityRepository;
import com.platinouss.bookrecommend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    public boolean exists(String email) {
        return userRepository.findUserByEmail(email).isPresent();
    }
    public void auth(User user) {
        Optional<User> oUser = userRepository.findUserByEmail(user.getEmail());
        if (oUser.isPresent()) {
            User u = oUser.get();
            if(!bCryptPasswordEncoder.matches(user.getPassword(), u.getPassword())) {
                throw new BadCredentialsException("아이디와 패스워드를 확인하세요");
            }
        } else {
            throw new BadCredentialsException("아이디와 패스워드를 확인하세요");
        }
    }

    public User find(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(RuntimeException::new);
    }

    public String getUserName(String email) {
        return userRepository.findUserByEmail(email).orElse(new User()).getName();
    }

}
