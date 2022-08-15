package com.platinouss.bookrecommend.repository;

import com.platinouss.bookrecommend.domain.User;
import com.platinouss.bookrecommend.domain.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
    List<UserHistory> findByUserId(Long id);
}
