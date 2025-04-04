package com.goorm.bakkuyoungapi.global.config.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;


@Component
@RequiredArgsConstructor
@Slf4j
public class JwtProvider { // jwt 생성,검증,관리 담당

    private static final long JWT_TOKEN_VALID = 1000L * 60 * 60 * 24; // 1일

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    //token Username 조회
    public String getUsernameFromToken(final String token) {
        return getClaimFromToken(token, Claims::getId);
    }

    //token 사용자 속성 정보 조회
    public <T> T getClaimFromToken(final String token, final Function<Claims, T> claimsResolver) {
        // token 유효성 검증
        if (Boolean.FALSE.equals(validateToken(token)))
            return null;

        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateAccessToken(final String id) {
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setId(id)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALID))
                .signWith(key)
                .compact();
    }

    //토큰검증
    public Boolean validateToken(final String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException e) {
            log.warn("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.warn("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.warn("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.warn("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.warn("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
