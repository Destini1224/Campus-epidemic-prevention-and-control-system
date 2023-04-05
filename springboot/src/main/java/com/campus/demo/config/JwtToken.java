package com.campus.demo.config;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

/**
 * Description：Jwt token相关
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/2/27
 */

public class JwtToken {

    private static long time = 1000*3600;
    private static String signature = "user";
    //创建token的方法
    public static String createToken(String username){
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                //header
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //payload
                .claim("username",username)
                .setSubject("user-test")
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(SignatureAlgorithm.HS256,signature)
                .compact();
        return jwtToken;
    }

    //校验token，布尔类型
    public static boolean checkToken(String token){
        if (token == null){
            return false;
        }
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(signature).parseClaimsJws(token); // 通过这个签名signature对token进行解析
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }
}