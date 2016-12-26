package com.lyun.estate.biz.user.repository;

import com.lyun.estate.biz.user.domain.User;
import com.lyun.estate.biz.user.repository.provider.UserSqlProvider;
import com.lyun.estate.biz.user.resources.LoginResource;
import com.lyun.estate.biz.user.resources.RegisterResource;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

@Mapper
public interface UserMapper {

    @InsertProvider(type = UserSqlProvider.class, method = "create")
    int createUser(User user);

    @SelectProvider(type = UserSqlProvider.class, method = "findUser")
    User findUser(RegisterResource registerResource);

    @SelectProvider(type = UserSqlProvider.class, method = "loginUser")
    User loginUser(LoginResource loginResource);
}
