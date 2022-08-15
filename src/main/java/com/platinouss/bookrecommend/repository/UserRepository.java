package com.platinouss.bookrecommend.repository;

import com.platinouss.bookrecommend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
}
