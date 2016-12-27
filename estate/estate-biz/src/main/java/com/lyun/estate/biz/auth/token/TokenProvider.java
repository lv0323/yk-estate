package com.lyun.estate.biz.auth.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
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

    private static final long hourInMills = 60 * 1000;

    public String generate(String subject) {
        return generate(subject, null, null, 2);
    }

    public String generate(String subject, long hour) {
        return generate(subject, null, null, hour);
    }

    public String generate(String subject, String claimKey, Object claimValue) {
        return generate(subject, claimKey, claimValue, 2);
    }

    public String generate(String subject, String claimKey, Object claimValue, long hour) {
        long now = (new Date()).getTime();
        Date validity = new Date(now + hour * hourInMills);

        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setExpiration(validity);
        if (claimKey != null && claimValue != null) {
            builder.claim(claimKey, claimValue);
        }
        return builder.compact();
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

    public String getSubject(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    @SuppressWarnings("unchecked")
    public <T> T getClaims(String token, String claimName, Class<T> c) {
        return (T) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get(claimName);
    }

}
