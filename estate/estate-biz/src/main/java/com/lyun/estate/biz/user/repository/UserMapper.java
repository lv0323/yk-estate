package com.lyun.estate.biz.user.repository;

import com.lyun.estate.biz.user.domain.User;
import com.lyun.estate.biz.user.repository.provider.UserSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @InsertProvider(type = UserSqlProvider.class, method = "create")
    int createUser(User user);

    @Select("select * from t_user where mobile = #{mobile}")
    User findByMobile(@Param("mobile") String mobile);
}
