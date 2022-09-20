package com.aviasi.perkasa.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class GenerateJWT {

    public static String createToken(String user ){

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setHeaderParam("alg", "HS256")
                .setId(user)
                .setIssuedAt(now)
                .setSubject("pemesanan")
                .setIssuer("tiket")
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
//                .setExpiration(new Date(System.currentTimeMillis() +  40 * 1000))
//                .signWith(SignatureAlgorithm.HS256, "EKSAD123");
                .signWith(SignatureAlgorithm.HS256, "P3rk4saviat0r313");

        return builder.compact();

    }


    public static Claims validateToken (String Token){

        Claims claims = null;

        claims = Jwts.parser().setSigningKey("P3rk4saviat0r313")
                .parseClaimsJws(Token)
                .getBody();

        return claims;

    }
}
