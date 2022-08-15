package com.platinouss.bookrecommend.domain.listener;

import com.platinouss.bookrecommend.domain.User;
import com.platinouss.bookrecommend.domain.UserHistory;
import com.platinouss.bookrecommend.repository.UserHistoryRepository;
import com.platinouss.bookrecommend.support.BeanUtils;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

public class UserEntityListener {
    @PostUpdate
    @PostPersist
    public void prePersistAndPreUpdate(Object o) {
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        User user = (User) o;

        UserHistory userHistory = new UserHistory();
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());
        userHistory.setUser(user);

        userHistoryRepository.save(userHistory);
    }
}