package com.lyun.estate.biz.user.repository.provider;

import com.lyun.estate.biz.user.domain.User;
import com.lyun.estate.biz.user.resources.LoginResource;
import com.lyun.estate.biz.user.resources.RegisterResource;
import com.lyun.estate.core.supports.BaseProvider;
import com.lyun.estate.core.supports.SQL_EX;
import org.springframework.util.StringUtils;

public class UserSqlProvider extends BaseProvider<User> {
    @Override
    protected String getEntityTable() {
        return "t_user";
    }

    @Override
    protected SQL_EX onCreate(SQL_EX sqlEx, User entity) {
        return sqlEx
                .VALUES_IF("user_name", "#{userName}", !StringUtils.isEmpty(entity.getUserName()))
                .VALUES_IF("real_name", "#{realName}", !StringUtils.isEmpty(entity.getRealName()))
                .VALUES_IF("card_id", "#{cardId}", !StringUtils.isEmpty(entity.getRealName()))
                .VALUES_IF("salt", "#{salt}", !StringUtils.isEmpty(entity.getSalt()))
                .VALUES_IF("hash", "#{hash}", !StringUtils.isEmpty(entity.getHash()))
                .VALUES_IF("email", "#{email}", !StringUtils.isEmpty(entity.getEmail()))
                .VALUES_IF("mobile", "#{mobile}", !StringUtils.isEmpty(entity.getMobile()))
                .VALUES_IF("description", "#{description}", !StringUtils.isEmpty(entity.getDescription()));
    }

    @Override
    protected SQL_EX onUpdate(SQL_EX sqlEx, User entity) {
        return sqlEx
                .SET_IF("user_name=#{userName}", !StringUtils.isEmpty(entity.getUserName()))
                .SET_IF("real_name=#{realName}", !StringUtils.isEmpty(entity.getRealName()))
                .SET_IF("card_id=#{cardId}", !StringUtils.isEmpty(entity.getCardId()))
                .SET_IF("salt=#{salt}", !StringUtils.isEmpty(entity.getSalt()))
                .SET_IF("hash=#{hash}", !StringUtils.isEmpty(entity.getHash()))
                .SET_IF("email=#{email}", !StringUtils.isEmpty(entity.getEmail()))
                .SET_IF("mobile=#{mobile}", !StringUtils.isEmpty(entity.getMobile()));
    }

    public String findUser(RegisterResource registerResource) {
        return new SQL_EX() {{
            SELECT("*");
            FROM(getEntityTable());
            WHERE_IF("user_name=#{userName}", !StringUtils.isEmpty(registerResource.getUserName()));
            WHERE_IF("mobile=#{mobile}", !StringUtils.isEmpty(registerResource.getMobile()));
            WHERE_IF("email=#{email}", !StringUtils.isEmpty(registerResource.getEmail()));
            WHERE("IS_DELETED='N'");
        }}.toString();
    }

    public String loginUser(LoginResource loginResource) {
        return new SQL_EX() {{
            SELECT("*");
            FROM(getEntityTable());
            WHERE_IF("mobile=#{mobile}", !StringUtils.isEmpty(loginResource.getMobile()));
            WHERE_IF("user_name=#{userName}", !StringUtils.isEmpty(loginResource.getUserName()));
            WHERE_IF("email=#{email}", !StringUtils.isEmpty(loginResource.getEmail()));
        }}.toString();
    }
}
