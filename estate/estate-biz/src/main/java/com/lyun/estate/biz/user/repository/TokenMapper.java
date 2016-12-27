package com.lyun.estate.biz.user.repository;

import com.lyun.estate.biz.user.domain.Token;
import com.lyun.estate.biz.user.repository.provider.TokenSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TokenMapper {
    @InsertProvider(type = TokenSqlProvider.class, method = "create")
    int create(Token token);
}
