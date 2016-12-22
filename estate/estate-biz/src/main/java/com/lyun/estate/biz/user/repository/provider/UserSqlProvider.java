package com.lyun.estate.biz.user.repository.provider;

import com.lyun.estate.biz.user.domain.User;
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
                .VALUES_IF("user_name", entity.getUserName(), !StringUtils.isEmpty(entity.getUserName()))
                .VALUES_IF("real_name", entity.getRealName(), !StringUtils.isEmpty(entity.getRealName()))
                .VALUES_IF("card_id", entity.getCardId(), !StringUtils.isEmpty(entity.getRealName()))
                .VALUES_IF("salt", entity.getSalt(), !StringUtils.isEmpty(entity.getSalt()))
                .VALUES_IF("hash", entity.getHash(), !StringUtils.isEmpty(entity.getHash()))
                .VALUES_IF("email", entity.getEmail(), !StringUtils.isEmpty(entity.getEmail()))
                .VALUES_IF("mobile", entity.getMobile(), !StringUtils.isEmpty(entity.getMobile()))
                .VALUES_IF("description", entity.getDescription(), !StringUtils.isEmpty(entity.getDescription()));
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
}
