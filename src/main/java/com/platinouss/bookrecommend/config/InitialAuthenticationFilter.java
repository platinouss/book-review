package com.platinouss.bookrecommend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.platinouss.bookrecommend.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
public class InitialAuthenticationFilter extends OncePerRequestFilter {

    @Lazy
    @Autowired
    private AuthenticationManager manager;

    private String sign = "smLTU2aq83j1jmJZj6owh4OrMNuatIj4fmJ";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        ObjectMapper objectMapper = new ObjectMapper();
        User requestUser = objectMapper.readValue(messageBody, User.class);

        String email = requestUser.getEmail();
        String password = requestUser.getPassword();

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        authentication = manager.authenticate(authentication);

        SecretKey key = Keys.hmacShaKeyFor(sign.getBytes(StandardCharsets.UTF_8));
        String accessToken = Jwts.builder()
                .setClaims(Map.of("email", email))
                .signWith(key)
                .compact();

        response.setHeader("Set-Cookie", makeCookie("access_token", accessToken));
//        response.addHeader("Set-Cookie", makeCookie("refresh_token", "bb"));
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/api/login");
    }

    private String makeCookie(String tokenName, String tokenValue) {
        ResponseCookie cookie = ResponseCookie.from(tokenName, tokenValue)
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .domain("localhost")
                .build();

        return cookie.toString();
    }
}
