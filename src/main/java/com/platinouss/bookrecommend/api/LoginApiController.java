package com.platinouss.bookrecommend.api;

import com.platinouss.bookrecommend.domain.User;
import com.platinouss.bookrecommend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class LoginApiController {

    private final UserService userService;

    @PostMapping("/api/login")
    public void login(@RequestBody User user, HttpServletResponse response) {
        userService.auth(user);
    }

    @GetMapping("/api/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Set-Cookie", deleteCookie("access_token"));
    }

    private String deleteCookie(String tokenName) {
        ResponseCookie cookie = ResponseCookie.from(tokenName, null)
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .domain("localhost")
                .maxAge(0)
                .build();

        return cookie.toString();
    }
}
