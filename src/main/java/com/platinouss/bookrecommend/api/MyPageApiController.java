package com.platinouss.bookrecommend.api;

import com.platinouss.bookrecommend.domain.User;
import com.platinouss.bookrecommend.dto.UserDto;
import com.platinouss.bookrecommend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class MyPageApiController {

    private final UserService userService;

    @GetMapping("/api/mypage")
    public UserDto mypage(Authentication authentication) {
        String email = authentication.getName();
        System.out.println(email);

        User user = userService.find(email);

        return new UserDto(user);
    }
}
