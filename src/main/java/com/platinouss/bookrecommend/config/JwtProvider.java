package com.platinouss.bookrecommend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final RedisTemplate<String, Object> redisTemplate;

    private static final long MINUTE = 60 * 1000L;
    private static final long ACCESS_TOKEN_EXPIRE_TIME = MINUTE * 30;
    private static final long REFRESH_TOKEN_EXPIRE_TIME = MINUTE * 60 * 24 * 3;


    @Value("${spring.jwt.sign.access}")
    private String accessSign;

    public String generateToken(Authentication authentication, HttpServletResponse response) {
        SecretKey accessKey = Keys.hmacShaKeyFor(accessSign.getBytes(StandardCharsets.UTF_8));
        String accessToken = makeJwtToken(authentication.getName(), accessKey, ACCESS_TOKEN_EXPIRE_TIME);

        String email = authentication.getName();
        String uuid = UUID.randomUUID().toString();
        redisTemplate.opsForSet().add(email, uuid);
        redisTemplate.expire(email, REFRESH_TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Set-Cookie", makeCookie("refresh_token", uuid));

        return accessToken;
    }

    public void removeToken(HttpServletRequest request, HttpServletResponse response) {
        String email = null;
        String uuid = null;
        String jwt = request.getHeader("Authorization");
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("refresh_token")) {
                uuid = cookie.getValue();
            }
        }
        SecretKey accessKey = Keys.hmacShaKeyFor(accessSign.getBytes(StandardCharsets.UTF_8));
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(accessKey)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
            email = claims.get("email").toString();
            response.setHeader("Set-Cookie", deleteCookie("refresh_token"));
            redisTemplate.opsForSet().remove(email, uuid);
        } catch (NullPointerException | IllegalArgumentException e) {
            try {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            } catch (IOException ex) {
                throw new RuntimeException("요청 시간 초과");
            }
        } catch (ExpiredJwtException e) {
            email = parseEmailFromJwt(jwt);
            response.setHeader("Set-Cookie", deleteCookie("refresh_token"));
            redisTemplate.opsForSet().remove(email, uuid);
        }
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

    private String parseEmailFromJwt(String jwt) {
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String[] splitData = jwt.split("\\.");
        String claims = new String(decoder.decode(splitData[1]));

        return new JSONObject(claims).get("email").toString();
    }
}
