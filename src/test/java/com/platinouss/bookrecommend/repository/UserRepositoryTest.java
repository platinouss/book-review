package com.platinouss.bookrecommend.repository;

import com.platinouss.bookrecommend.domain.User;
import com.platinouss.bookrecommend.domain.UserHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Test
    @Transactional
    void findAllTest() {
        List<User> users = userRepository.findAll();

        System.out.println(users);
    }

    @Test
    void crudTest() {
        User user1 = new User();
        user1.setName("platinouss");
        user1.setEmail("platinouss@gmail.com");

        userRepository.save(user1);
        userRepository.findAll().forEach(System.out::println);

        user1.setEmail("changed@gmail.com");
        userRepository.findAll().forEach(System.out::println);

        List<UserHistory> histories = userHistoryRepository.findByUserId(user1.getId());
        System.out.println(histories);
    }
}