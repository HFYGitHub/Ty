package com.ty.common.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

    // 生成 key（HS256）
    private static SecretKey getKey(String secret) {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * 生成 token
     */
    public static String createToken(String secret, Map<String, Object> claims, Date expireDate) {
        return Jwts.builder()
                .setClaims(claims) // payload
                .setExpiration(expireDate) // 过期时间
                .signWith(getKey(secret), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 解析 token
     */
    public static Claims parseToken(String secret, String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey(secret))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}