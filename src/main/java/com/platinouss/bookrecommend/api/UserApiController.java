package com.platinouss.bookrecommend.api;

import com.platinouss.bookrecommend.dto.UserDto;
import com.platinouss.bookrecommend.dto.UserLoginDto;
import com.platinouss.bookrecommend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/login")
    public String login(@RequestBody UserLoginDto userLoginDto, HttpServletResponse response) {
        return userService.login(userLoginDto, response);
    }

    @PostMapping("/api/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.logout(request, response);
    }

    @GetMapping("/api/mypage")
    public UserDto mypage(Authentication authentication) {
        String email = authentication.getName();
        log.info(email);
        return new UserDto(userService.find(email));
    }
}
