package com.lyun.estate.biz.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenProvider {

    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private static final String secretKey = "123456";

    private static final long tokenValidityInMills = 7 * 24 * 60 * 1000;

    public String generate(String username) {
        long now = (new Date()).getTime();
        Date validity = new Date(now + tokenValidityInMills);
        return Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setExpiration(validity)
                .claim("username", username)
                .compact();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.info("invalid jwt signature: {}", e.getMessage());
            return false;
        }
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

}
