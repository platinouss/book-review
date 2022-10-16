package com.platinouss.bookrecommend.service;

import com.platinouss.bookrecommend.domain.Authority;
import com.platinouss.bookrecommend.domain.User;
import com.platinouss.bookrecommend.domain.enums.Gender;
import com.platinouss.bookrecommend.model.CustomUserDetails;
import com.platinouss.bookrecommend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void before() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("1. 사용자 추가")
    void test1() {

         User user = User.builder()
                 .name("platinouss")
                 .password("1234")
                 .email("platinouss@gmail.com")
                 .gender(Gender.MALE)
                 .build();

        userService.addUser(user);

        String email = "";
        if(userRepository.findUserByName(user.getName()).isPresent()) {
            email = userRepository.findUserByName(user.getName()).get().getEmail();
        }

        assertEquals("platinouss@gmail.com", email);
    }
}
