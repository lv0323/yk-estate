package com.lyun.estate.biz.auth.token.repository;

import com.lyun.estate.biz.auth.token.TokenEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TokenMapper {

    @Insert("INSERT INTO T_TOKEN(USER_ID, CLIENT_ID, TOKEN, EXPIRED_TIME, REFRESH_TOKEN) " +
            " VALUES(#{userId}, #{clientId}, #{token}, #{expiredTime}, #{refreshToken})")
    @Options(useGeneratedKeys = true)
    int createToken(TokenEntity tokenEntity);

    @Select("SELECT * FROM T_TOKEN WHERE TOKEN = #{token} AND EXPIRED_TIME > NOW() LIMIT 1")
    TokenEntity findToken(String token);

    @Update("UPDATE T_TOKEN SET EXPIRED_TIME = NOW() WHERE TOKEN = #{token}")
    int invalidToken(String token);

    @Update("UPDATE T_TOKEN SET EXPIRED_TIME = NOW() WHERE USER_ID = #{userId}")
    int invalidAllUserTokens(String userId);

}
