package com.lyun.estate.biz.auth.token;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class TokenProvider {

    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    @Value("${token.secret.key}")
    private String secretKey;

    @Value("${token.refresh.key}")
    private String refreshKey;

    @Autowired
    private TokenMapper tokenMapper;

    private static final long defaultTokenHour = 2;
    private static final long defaultRefreshTokenHour = 7 * 24;
    private static final long hourInMills = 60 * 60 * 1000;

    public JWTToken generate(String subject, int clientId) {
        return generate(subject, clientId, defaultTokenHour);
    }

    public JWTToken generate(String subject, int clientId, long hour) {
        return generate(subject, clientId, hour, null);
    }

    public JWTToken generate(String subject, int clientId, HashMap<String, Object> claims) {
        return generate(subject, clientId, defaultTokenHour, claims);
    }

    public JWTToken generate(String subject, int clientId, long hour, HashMap<String, Object> claims) {
        long now = (new Date()).getTime();
        hour = checkHour(hour);
        Date validity = new Date(now + hour * hourInMills);
        Date refreshValidity = new Date(now + (defaultRefreshTokenHour + hour) * hourInMills);
        String token = generateToken(subject, validity, claims);
        String refreshToken = generateRefreshToken(subject, refreshValidity);
        tokenMapper.createToken(new TokenEntity(subject, clientId, token, validity, refreshToken));
        return new JWTToken(token, refreshToken);
    }

    private String generateToken(String subject, Date validity, HashMap<String, Object> claims) {
        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setExpiration(validity);
        if (claims != null) {
            for (String key : claims.keySet()) {
                builder.claim(key, claims.get(key));
            }
        }
        return builder.compact();
    }

    private String generateRefreshToken(String subject, Date refreshValidity) {
        return Jwts.builder()
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS512, refreshKey)
                .setExpiration(refreshValidity).compact();
    }

    private long checkHour(long hour) {
        if (hour <= 0) hour = 2;
        if (hour >= 168) hour = 168;
        return hour;
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
            return tokenMapper.findToken(token) != null;
        } catch (SignatureException e) {
            logger.info("invalid jwt signature: {}", e.getMessage());
            return false;
        }
    }

    public int invalidToken(String token) {
        int rows = tokenMapper.invalidToken(token);
        logger.info("invalid token {}, update {} row(s)", token, rows);
        return rows;
    }

    public int invalidAllUserToken(String userId) {
        int rows = tokenMapper.invalidAllUserTokens(userId);
        logger.info("invalid user {}'s tokens, update {} row(s)", userId, rows);
        return rows;
    }

    public String getSubject(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public Object getClaim(String token, String claim) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get(claim);
    }

}
