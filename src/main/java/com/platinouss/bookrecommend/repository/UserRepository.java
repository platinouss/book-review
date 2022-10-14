package com.platinouss.bookrecommend.repository;

import com.platinouss.bookrecommend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {
    Optional<User> findUserByName(String name);
}
