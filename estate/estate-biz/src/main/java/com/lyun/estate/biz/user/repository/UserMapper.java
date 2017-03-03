package com.lyun.estate.biz.user.repository;

import com.lyun.estate.biz.user.domain.User;
import com.lyun.estate.biz.user.repository.provider.UserSqlProvider;
import com.lyun.estate.biz.user.resources.ChangePasswordResource;
import com.lyun.estate.biz.user.resources.LoginResource;
import com.lyun.estate.biz.user.resources.RegisterResource;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @InsertProvider(type = UserSqlProvider.class, method = "create")
    int createUser(User user);

    @SelectProvider(type = UserSqlProvider.class, method = "findUser")
    User findUser(RegisterResource registerResource);

    @Select("select * from t_user where mobile = #{mobile} and is_deleted='N'")
    User findUserByMobile(@Param("mobile") String mobile);

    @SelectProvider(type = UserSqlProvider.class, method = "changePasswordUser")
    User changePasswordUser(ChangePasswordResource changePasswordResource);

    @SelectProvider(type = UserSqlProvider.class, method = "loginUser")
    User loginUser(LoginResource loginResource);

    @UpdateProvider(type = UserSqlProvider.class, method = "update")
    int updateUser(User user);

    @Select("select * from t_user where id = #{id} and is_deleted='N'")
    User findUserById(Long aLong);
}
