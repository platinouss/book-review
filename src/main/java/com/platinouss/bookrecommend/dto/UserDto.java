package com.platinouss.bookrecommend.dto;

import com.platinouss.bookrecommend.domain.Review;
import com.platinouss.bookrecommend.domain.User;
import com.platinouss.bookrecommend.domain.enums.Gender;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDto {
    private String email;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    List<ReviewDto> reviews;

    public UserDto(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.gender = user.getGender();
        this.reviews = user.getReviews().stream()
                .map(review -> new ReviewDto(review))
                .collect(Collectors.toList());
    }
}
