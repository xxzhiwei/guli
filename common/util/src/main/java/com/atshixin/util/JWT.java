package com.atshixin.util;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JWT {
    private static final int EXPIRE_DAYS = 1;
    private static final long EXPIRE_TIME = 1000 * 60 * 60 * 24 * EXPIRE_DAYS; // token过期时间（一天

    private static final String SECRET = "guli-school";
    private static final String PREFIX = "Bearer";

    public static String getTokenThroughRequest(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");

        if (StringUtils.isEmpty(authorization)) {
            return "";
        }

        String[] authorizationStrArr = authorization.split(" ");

        // 1. 应该以Bearer开头
        if (!authorization.startsWith(PREFIX)) {
            return "";
        }

        // 2. 应该包含['Bearer', 'xxx']
        if (authorizationStrArr.length != 2) {
            return "";
        }

        return authorizationStrArr[1];
    }

    public static boolean checkToken(HttpServletRequest request) {
        String token = getTokenThroughRequest(request);
        return checkToken(token);
    }

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

    public static TokenR generateToken(String userId, String userName) {
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        String token = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("guli-user")
                .setExpiration(expireDate)
                .claim("userId", userId)
                .claim("userName", userName)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
        return new TokenR(token, expireDate.getTime(), EXPIRE_DAYS);
    }

    public static String getUserIdByToken(HttpServletRequest request) {
        String token = getTokenThroughRequest(request);
        return getUserIdByToken(token);
    }

    public static String getUserIdByToken(String token) {
        Claims claims = parseToken(token);
        if (claims == null) {
            return "";
        }
        return (String) claims.get("userId");
    }

    private static Claims parseToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }

        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return claimsJws.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
//        Date date = new Date(System.currentTimeMillis() + EXPIRE);
//        System.out.println(date.getTime());
//
//        String authorization = "Bearer ";
//
//        String[] strings = authorization.split(" ");
//
//        System.out.println(strings.length);
    }
}
