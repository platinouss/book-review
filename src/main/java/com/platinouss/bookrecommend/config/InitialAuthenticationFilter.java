package com.platinouss.bookrecommend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.platinouss.bookrecommend.vo.UserVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
public class InitialAuthenticationFilter extends OncePerRequestFilter {

    @Lazy
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${spring.jwt.sign.access}")
    private String accessSign;

    private static final long MINUTE = 60 * 1000L;
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 30 * MINUTE;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException {

        if (request.getServletPath().equals("/api/logout")) {
            try {
                SecretKey accessKey = Keys.hmacShaKeyFor(accessSign.getBytes(StandardCharsets.UTF_8));
                String jwt = request.getHeader("Authorization");
                Claims claims = Jwts.parserBuilder().setSigningKey(accessKey)
                        .build()
                        .parseClaimsJws(jwt)
                        .getBody();
                String email = claims.get("email").toString();
                String uuid = null;
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals("refresh_token")) {
                        uuid = cookie.getValue();
                    }
                }
                response.setHeader("Set-Cookie", deleteCookie("refresh_token"));
                redisTemplate.opsForSet().remove(email, uuid);
                return;
            } catch (NullPointerException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        UserVo requestUser = new ObjectMapper().readValue(messageBody, UserVo.class);
        String email = requestUser.getEmail();
        String password = requestUser.getPassword();

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        manager.authenticate(authentication);

        String uuid = UUID.randomUUID().toString();
        redisTemplate.opsForSet().add(email, uuid);

        SecretKey accessKey = Keys.hmacShaKeyFor(accessSign.getBytes(StandardCharsets.UTF_8));
        String accessToken = makeJwtToken(email, accessKey, ACCESS_TOKEN_EXPIRE_TIME);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new JSONObject().put("access_token", accessToken).toString());
        response.setHeader("Set-Cookie", makeCookie("refresh_token", uuid));
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        List<String> uris = List.of("/api/login", "/api/logout");

        String accessUri = request.getServletPath();
        for (String uri : uris) {
            if (uri.equals(accessUri)) {
                return false;
            }
        }
        return true;
    }

    private String makeJwtToken(String email, SecretKey key, Long time) {
        Date now = new Date();
        return Jwts.builder()
                .setClaims(Map.of("email", email))
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + time))
                .signWith(key)
                .compact();
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
