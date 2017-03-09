package com.lyun.estate.biz.user.repository.provider;

import com.lyun.estate.biz.user.domain.User;
import com.lyun.estate.biz.user.resources.ChangePasswordResource;
import com.lyun.estate.biz.user.resources.LoginResource;
import com.lyun.estate.biz.user.resources.RegisterResource;
import com.lyun.estate.core.repo.SQL;
import com.lyun.estate.core.supports.BaseProvider;
import org.springframework.util.StringUtils;

public class UserSqlProvider extends BaseProvider<User> {
    @Override
    protected String getEntityTable() {
        return "t_user";
    }

    @Override
    protected SQL onCreate(SQL sqlEx, User entity) {
        return sqlEx
                .VALUES_IF("user_name", "#{userName}", !StringUtils.isEmpty(entity.getUserName()))
                .VALUES_IF("real_name", "#{realName}", !StringUtils.isEmpty(entity.getRealName()))
                .VALUES_IF("card_id", "#{cardId}", !StringUtils.isEmpty(entity.getRealName()))
                .VALUES_IF("salt", "#{salt}", !StringUtils.isEmpty(entity.getSalt()))
                .VALUES_IF("hash", "#{hash}", !StringUtils.isEmpty(entity.getHash()))
                .VALUES_IF("email", "#{email}", !StringUtils.isEmpty(entity.getEmail()))
                .VALUES_IF("mobile", "#{mobile}", !StringUtils.isEmpty(entity.getMobile()))
                .VALUES_IF("description", "#{description}", !StringUtils.isEmpty(entity.getDescription()))
                .VALUES_IF("client_id", "#{clientId}", entity.getClientId() > 0);
    }

    @Override
    protected SQL onUpdate(SQL sqlEx, User entity) {
        return sqlEx
                .SET_IF("user_name=#{userName}", !StringUtils.isEmpty(entity.getUserName()))
                .SET_IF("real_name=#{realName}", !StringUtils.isEmpty(entity.getRealName()))
                .SET_IF("card_id=#{cardId}", !StringUtils.isEmpty(entity.getCardId()))
                .SET_IF("salt=#{salt}", !StringUtils.isEmpty(entity.getSalt()))
                .SET_IF("hash=#{hash}", !StringUtils.isEmpty(entity.getHash()))
                .SET_IF("email=#{email}", !StringUtils.isEmpty(entity.getEmail()))
                .SET_IF("mobile=#{mobile}", !StringUtils.isEmpty(entity.getMobile()))
                .SET_IF("description=#{description}", !StringUtils.isEmpty(entity.getDescription()))
                .SET_IF("client_id=#{clientId}", entity.getClientId() > 0);
    }

    public String findUser(RegisterResource registerResource) {
        return new SQL().SELECT("*")
                .FROM(getEntityTable())
                .WHERE_IF("user_name=#{userName}", !StringUtils.isEmpty(registerResource.getUserName()))
                .WHERE_IF("email=#{email}", !StringUtils.isEmpty(registerResource.getEmail()))
                .WHERE("is_deleted='N'").toString();
    }

    public String loginUser(LoginResource loginResource) {
        return new SQL().SELECT("*")
                .FROM(getEntityTable())
                .WHERE_IF("mobile=#{mobile}", !StringUtils.isEmpty(loginResource.getMobile()))
                .WHERE_IF("user_name=#{userName}", !StringUtils.isEmpty(loginResource.getUserName()))
                .WHERE_IF("email=#{email}", !StringUtils.isEmpty(loginResource.getEmail()))
                .WHERE("is_deleted='N'")
                .toString();
    }

    public String changePasswordUser(ChangePasswordResource changePasswordResource) {
        return new SQL().SELECT("*")
                .FROM(getEntityTable())
                .WHERE_IF("id=#{userId}", changePasswordResource.getUserId() != 0)
                .WHERE("is_deleted='N'").toString();
    }
}
