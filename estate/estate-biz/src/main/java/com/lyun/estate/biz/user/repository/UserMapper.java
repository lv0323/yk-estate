package com.lyun.estate.biz.user.repository;

import com.lyun.estate.biz.user.domain.User;
import com.lyun.estate.biz.user.repository.provider.UserSqlProvider;
import com.lyun.estate.biz.user.resources.ChangePasswordResource;
import com.lyun.estate.biz.user.resources.LoginResource;
import com.lyun.estate.biz.user.resources.RegisterResource;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

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
}
