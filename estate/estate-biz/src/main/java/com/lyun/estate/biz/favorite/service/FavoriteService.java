package com.lyun.estate.biz.favorite.service;

import com.google.common.collect.Lists;
import com.lyun.estate.biz.fang.def.BizType;
import com.lyun.estate.biz.favorite.def.FavoriteType;
import com.lyun.estate.biz.favorite.entity.Favorite;
import com.lyun.estate.biz.favorite.repository.FavoriteMapper;
import com.lyun.estate.biz.spec.fang.rest.entity.FangSummary;
import com.lyun.estate.biz.spec.fang.rest.service.FangService;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.core.supports.context.RestContext;
import com.lyun.estate.core.supports.exceptions.EasyCodeException;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.lyun.estate.core.supports.exceptions.ExCode.PARAM_NULL;

@Service
public class FavoriteService {

    @Autowired
    private RestContext restContext;

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Autowired
    private FangService fangService;


    private List<DomainType> supportedTypes = Lists.newArrayList(DomainType.XIAO_QU, DomainType.FANG);

    @Transactional
    public boolean createFavorite(long targetId, DomainType domainType) {

        checkDomainType(domainType);

        if (isFavorite(targetId, domainType)) {
            return true;
        }
        long userId = restContext.getUserId();

        Favorite favorite = new Favorite().setTargetId(targetId).setDomainType(domainType).setFollowerId(userId);

        if (domainType == DomainType.FANG) {
            FangSummary fangSummary = fangService.getSummary(targetId);
            if (fangSummary.getBizType() == BizType.SELL) {
                favorite.setType(FavoriteType.FANG_SELL);
            } else if (fangSummary.getBizType() == BizType.RENT) {
                favorite.setType(FavoriteType.FANG_RENT);
            }
        } else if (domainType == DomainType.XIAO_QU) {
            favorite.setType(FavoriteType.XIAO_QU);
        }

        if (1 != favoriteMapper.createFavorite(favorite)) {
            throw new EasyCodeException("favorite.error", "关注失败");
        } else {
            return true;
        }
    }

    private void checkDomainType(DomainType domainType) {
        if (!supportedTypes.contains(domainType)) {
            throw new EstateException(ExCode.FAVORITE_TYPE_NOT_SUPPORTED);
        }
    }

    @Transactional
    public boolean cancelFavorite(long targetId, DomainType domainType) {
        checkDomainType(domainType);

        if (!isFavorite(targetId, domainType)) {
            return true;
        }
        long userId = restContext.getUserId();
        if (1 != favoriteMapper.deleteFavorite(targetId, domainType, userId)) {
            throw new EasyCodeException("cancel.favorite.error", "取消关注失败");
        } else {
            return true;
        }
    }

    public boolean isFavorite(long targetId, DomainType domainType) {
        return null != favoriteMapper.findFavorite(targetId, domainType, restContext.getUserId());
    }

    public List<Favorite> getFavorite(DomainType domainType, Long targetId) {
        if (domainType == null) {
            throw new EstateException(PARAM_NULL, "domainType");
        }
        if (targetId == null) {
            throw new EstateException(PARAM_NULL, "targetId");
        }
        return favoriteMapper.getFavorites(domainType, targetId);
    }
}
