package com.lyun.estate.biz.auth.token.repository;

import com.lyun.estate.biz.auth.token.Token;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TokenMapper {

    @Insert("INSERT INTO T_TOKEN(USER_ID, REFRESH_FROM ,CLIENT_ID, TOKEN, EXPIRED_TIME, REFRESH_TOKEN, REFRESH_EXPIRED_TIME, REFRESH_END_TIME) " +
            " VALUES(#{userId},#{refreshFrom}, #{clientId}, #{token}, #{expiredTime}, #{refreshToken},#{refreshExpiredTime},#{refreshEndTime})")
    @Options(useGeneratedKeys = true)
    int createToken(Token token);

    @Select("SELECT * FROM T_TOKEN WHERE TOKEN = #{token} AND EXPIRED_TIME > NOW() LIMIT 1")
    Token findToken(String token);

    @Update("UPDATE T_TOKEN SET EXPIRED_TIME = NOW(), REFRESH_EXPIRED_TIME = NOW() WHERE TOKEN = #{token}")
    int invalidTokenByToken(String token);

    @Update("UPDATE T_TOKEN SET EXPIRED_TIME = NOW(), REFRESH_EXPIRED_TIME = NOW() WHERE id = #{id}")
    int invalidToken(Long id);

    @Update("UPDATE T_TOKEN SET EXPIRED_TIME = NOW(), REFRESH_EXPIRED_TIME = NOW() WHERE USER_ID = #{userId}")
    int invalidAllUserTokens(String userId);

    @Select("SELECT * FROM T_TOKEN WHERE REFRESH_TOKEN = #{refreshToken} AND REFRESH_EXPIRED_TIME > NOW() LIMIT 1")
    Token findTokenByRefreshToken(String refreshToken);

    @Select("SELECT * FROM T_TOKEN WHERE ID = #{id}")
    Token findOne(Long id);
}
