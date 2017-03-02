package com.lyun.estate.biz.auth.token;

import com.lyun.estate.biz.auth.token.repository.TokenMapper;
import com.lyun.estate.biz.support.clock.Clock;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;

@Component
public class TokenProvider {

    private static final long TOKEN_EXPIRE_DAY = 1L;
    private static final long REFRESH_TOKEN_EXPIRE_DAY = 14L;
    private static final long REFRESH_TOKEN_END_DAY = 30L;
    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    @Value("${token.secret.key}")
    private String secretKey;
    @Value("${token.refresh.key}")
    private String refreshKey;
    @Autowired
    private TokenMapper tokenMapper;
    @Autowired
    private Clock clock;


    public JWTToken generate(String subject, int clientId, HashMap<String, Object> claims) {

        Instant now = clock.nowInstant();
        Date expireTime = Date.from(now.plus(TOKEN_EXPIRE_DAY, ChronoUnit.DAYS));
        Date refreshExpireTime = Date.from(now.plus(REFRESH_TOKEN_EXPIRE_DAY, ChronoUnit.DAYS));
        Date refreshEndTime = Date.from(now.plus(REFRESH_TOKEN_END_DAY, ChronoUnit.DAYS));
        String token = generateToken(subject, expireTime, claims);
        String refreshToken = generateRefreshToken(subject, refreshExpireTime);
        tokenMapper.createToken(new Token().setUserId(subject)
                .setRefreshFrom(0L)
                .setClientId(clientId)
                .setToken(token)
                .setExpiredTime(expireTime)
                .setRefreshToken(refreshToken)
                .setRefreshExpiredTime(refreshExpireTime)
                .setRefreshEndTime(refreshEndTime));
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

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
            return tokenMapper.findToken(token) != null;
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException e) {
            logger.info("invalid jwt signature: {}", e.getMessage());
            return false;
        }
    }

    public int invalidToken(String token) {
        int rows = tokenMapper.invalidTokenByToken(token);
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

    public Token checkRefreshToken(String refreshToken) {
        try {
            Jwts.parser().setSigningKey(refreshKey).parseClaimsJws(refreshToken).getBody().getSubject();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException e) {
            throw new EstateException(ExCode.TOKEN_REFRESH_INVALID);
        }
        Token token = tokenMapper.findTokenByRefreshToken(refreshToken);
        if (token == null) {
            throw new EstateException(ExCode.TOKEN_REFRESH_INVALID);
        }
        if (clock.nowInstant().isAfter(token.getRefreshEndTime().toInstant())) {
            throw new EstateException(ExCode.TOKEN_REFRESH_END);
        }
        return token;
    }

    @Transactional
    public JWTToken refresh(Long id, String subject, HashMap<String, Object> claims) {
        Token oldToken = tokenMapper.findOne(id);
        Instant now = clock.nowInstant();
        Date expireTime = Date.from(now.plus(TOKEN_EXPIRE_DAY, ChronoUnit.DAYS));
        Date refreshExpireTime = Date.from(now.plus(REFRESH_TOKEN_EXPIRE_DAY, ChronoUnit.DAYS));
        Date refreshEndTime = oldToken.getRefreshEndTime();
        String token = generateToken(subject, expireTime, claims);
        String refreshToken = generateRefreshToken(subject, refreshExpireTime);
        tokenMapper.invalidToken(id);
        tokenMapper.createToken(new Token().setUserId(subject)
                .setRefreshFrom(id)
                .setClientId(oldToken.getClientId())
                .setToken(token)
                .setExpiredTime(expireTime)
                .setRefreshToken(refreshToken)
                .setRefreshExpiredTime(refreshExpireTime)
                .setRefreshEndTime(refreshEndTime));
        return new JWTToken(token, refreshToken);
    }
}
