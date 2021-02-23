package com.atshixin.util;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.util.Date;

public class JWT {

    private static final long EXPIRE = 1000 * 60 * 60 * 24; // token过期时间（一天

    private static final String SECRET = "guli-school";

    public static boolean checkToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return false;
        }

        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String generateToken(String userId, String userName) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("guli-user")
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim("userId", userId)
                .claim("userName", userName)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public static String getUserIdByToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String) claims.get("userId");
    }
}
