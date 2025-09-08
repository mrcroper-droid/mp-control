package com.wcsoft.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
@Data
public class JwtTokenUtils {
    public static final String TOKEN_HEADER = "Authorization";
    private static final long EXPIRATION = 3600L;
    private static final String ISS = "jie";
    private static final String SECRET = "5eb4x7dpl";

    // 创建token
    public static String createToken(String username,String role) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("role", role);
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, JwtTokenUtils.SECRET)
                .setClaims(map)
                .setIssuer("jie")
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JwtTokenUtils.EXPIRATION * 1000))
                .compact();
    }

    // 从token中获取用户名
    public static String getUsername(String token){
        return getTokenBody(token).getSubject();
    }

    // 获取用户角色
    public static String getUserRole(String token){
        return (String) getTokenBody(token).get("role");
    }

    // 是否已过期
    public static boolean isExpiration(String token) {
        try {
            return getTokenBody(token).getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    private static Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(JwtTokenUtils.SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
