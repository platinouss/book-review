package com.platinouss.bookrecommend.service;

import com.platinouss.bookrecommend.config.JwtProvider;
import com.platinouss.bookrecommend.domain.Authority;
import com.platinouss.bookrecommend.domain.User;
import com.platinouss.bookrecommend.dto.UserLoginDto;
import com.platinouss.bookrecommend.repository.AuthorityRepository;
import com.platinouss.bookrecommend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        Authority a = Authority.builder()
                        .user(user)
                        .authority("ROLE_USER")
                        .build();
        authorityRepository.save(a);

        user.setAuthorities(authorityRepository.findAuthorityByUser(user));

        userRepository.save(user);
    }

    public boolean exists(String email) {
        return userRepository.findUserByEmail(email).isPresent();
    }

    public String login(UserLoginDto user, HttpServletResponse response) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        authenticationManager.authenticate(authentication);
        return jwtProvider.generateToken(authentication, response);
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        jwtProvider.removeToken(request, response);
    }

    public User find(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(RuntimeException::new);
    }
}
