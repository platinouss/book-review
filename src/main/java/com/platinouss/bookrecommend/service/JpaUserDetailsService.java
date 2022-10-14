package com.platinouss.bookrecommend.service;

import com.platinouss.bookrecommend.domain.User;
import com.platinouss.bookrecommend.model.CustomUserDetails;
import com.platinouss.bookrecommend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> supplier = () ->
                new UsernameNotFoundException("인증 실패");

        User user = userRepository.findUserByName(username)
                .orElseThrow(supplier);

        return new CustomUserDetails(user);
    }
}
