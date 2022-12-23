package com.platinouss.bookrecommend.config;

import com.platinouss.bookrecommend.model.CustomUserDetails;
import com.platinouss.bookrecommend.service.JpaUserDetailsService;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final long MINUTE = 60 * 1000L;
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 30 * MINUTE;

    @Value("${spring.jwt.sign.access}")
    private String accessSign;

    private final RedisTemplate<String, Object> redisTemplate;
    private final JpaUserDetailsService jpaUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader("Authorization");
        if (jwt == null) {
            filterChain.doFilter(request, response);
            return;
        }
        Claims claims = null;
        String email = getEmailByJwt(jwt, response);
        SecretKey accessKey = Keys.hmacShaKeyFor(accessSign.getBytes(StandardCharsets.UTF_8));
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(accessKey)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (ExpiredJwtException e) {
            String uuid = null;
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("refresh_token")) {
                    uuid = cookie.getValue();
                }
            }
            if (uuid == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            if (!renewToken(jwt, accessKey, uuid, response)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        CustomUserDetails userDetails = jpaUserDetailsService.loadUserByUsername(email);

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
            var auth = new UsernamePasswordAuthenticationToken(userDetails, null, List.of(grantedAuthority));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

    private boolean renewToken(String jwt, SecretKey key, String uuid, HttpServletResponse response) throws IOException {
        String email = getEmailByJwt(jwt, response);
        String refreshJwt = makeJwtToken(email, key, ACCESS_TOKEN_EXPIRE_TIME);
        if (redisTemplate.opsForValue().setIfAbsent(email, uuid)) {
            removeAllRefreshToken(email);
            return false;
        }
        redisTemplate.opsForSet().remove(email, uuid);

        String refreshUuid = UUID.randomUUID().toString();
        redisTemplate.opsForSet().add(email, refreshUuid);
        response.setHeader("Authorization", refreshJwt);
        response.setHeader("Set-Cookie", makeCookie("refresh_token", refreshUuid));
        return true;
    }

    private void removeAllRefreshToken(String email) {
        redisTemplate.opsForSet().remove(email);
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

    private String makeJwtToken(String email, SecretKey key, Long time) {
        Date now = new Date();
        return Jwts.builder()
                .setClaims(Map.of("email", email))
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + time))
                .signWith(key)
                .compact();
    }

    private String getEmailByJwt(String jwt, HttpServletResponse response) throws IOException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        try {
            String[] splitData = jwt.split("\\.");
            String claims = new String(decoder.decode(splitData[1]));
            return new JSONObject(claims).get("email").toString();
        } catch (NullPointerException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
    }
}
