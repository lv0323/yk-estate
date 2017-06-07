package com.lyun.estate.op.dianping.user.repo;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * Created by localuser on 2017/5/15.
 */
@Repository
public interface UserRepo {

    @Select("select count(*) from t_op_dianping_user where openId =#{openId}")
    int registered(@Param("openId") String openId);

    @Insert("insert into t_op_dianping_user(user_type, openid, session_key) " +
            "values(#{userType},#{openId}, #{sessionKey})")
    int register(@Param("userType") String userType,
                 @Param("openId") String openId,
                 @Param("sessionKey") String sessionKey);

    @Update("update t_op_dianping_user set session_key = #{sessionKey} where openid=#{openId}")
    int updateSessionKey(@Param("openId") String openId, @Param("sessionKey") String sessionKey);

    @Update("update t_op_dianping_user set nicky = #{nicky}, avatar= #{avatar} where id=#{userId}")
    int updateUserInfo(@Param("userId")long userId, @Param("nicky") String nicky, @Param("avatar") String avatar);

    @Select("select id from t_op_dianping_user where openid = #{openId}")
    int getByOpenId(@Param("openId") String openId);

}
