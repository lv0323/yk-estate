package com.lyun.estate.biz.user.repository.provider;

import com.lyun.estate.biz.user.domain.Token;
import com.lyun.estate.core.supports.BaseProvider;
import com.lyun.estate.core.supports.SQL_EX;
import org.springframework.util.StringUtils;

public class TokenSqlProvider extends BaseProvider<Token> {
    @Override
    protected String getEntityTable() {
        return "t_token";
    }

    @Override
    protected SQL_EX onCreate(SQL_EX sqlEx, Token entity) {
        return sqlEx
                .VALUES_IF("hash","#{hash}",!StringUtils.isEmpty(entity.getHash()))
                .VALUES_IF("expire_time","#{expireTime}",!StringUtils.isEmpty(entity.getExpireTime()));
    }

    @Override
    protected SQL_EX onUpdate(SQL_EX sqlEx, Token entity) {
        return null;
    }
}
