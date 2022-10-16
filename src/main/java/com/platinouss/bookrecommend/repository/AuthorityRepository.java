package com.platinouss.bookrecommend.repository;

import com.platinouss.bookrecommend.domain.Authority;
import com.platinouss.bookrecommend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    List<Authority> findAuthorityByUser(User user);
}
