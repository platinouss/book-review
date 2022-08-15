package com.platinouss.bookrecommend.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class UserTest {
    @Test
    void userTest() {
        User user = new User();
        user.setName("test");
        user.setEmail("test@gmail.com");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        System.out.println("user : " + user);
    }
}