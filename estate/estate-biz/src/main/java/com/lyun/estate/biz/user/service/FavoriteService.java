package com.lyun.estate.biz.user.service;

import com.lyun.estate.biz.spec.common.DomainType;
import com.lyun.estate.biz.user.repository.FavoriteMapper;
import com.lyun.estate.core.supports.ExecutionContext;
import com.lyun.estate.core.supports.exceptions.EstateBizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FavoriteService {
    @Autowired
    ExecutionContext executionContext;
    @Autowired
    FavoriteMapper favoriteMapper;

    @Transactional
    public boolean createFavorite(long targetId, DomainType domainType) {
        if (isFavorite(targetId, domainType)) {
            return true;
        }
        long userId = Long.valueOf(executionContext.getUserId());
        if (1 != favoriteMapper.createFavorite(targetId, domainType, userId)) {
            throw new EstateBizException("favorite.error", "关注失败");
        } else {
            return true;
        }
    }

    @Transactional
    public boolean favoriteCancle(long targetId, DomainType domainType) {
        if (!isFavorite(targetId, domainType)) {
            return true;
        }
        long userId = Long.valueOf(executionContext.getUserId());
        if (1 != favoriteMapper.deleteFavorite(targetId, domainType, userId)) {
            throw new EstateBizException("cancel.favorite.error", "取消关注失败");
        } else {
            return true;
        }
    }

    public boolean isFavorite(long targetId, DomainType domainType) {
        return null != favoriteMapper.findFavorite(targetId, domainType, Long.valueOf(executionContext.getUserId()));
    }
}
