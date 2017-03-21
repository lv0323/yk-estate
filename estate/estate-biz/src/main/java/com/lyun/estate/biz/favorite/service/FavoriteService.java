package com.lyun.estate.biz.favorite.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Lists;
import com.lyun.estate.biz.favorite.def.FavoriteType;
import com.lyun.estate.biz.favorite.entity.Favorite;
import com.lyun.estate.biz.favorite.repository.FavoriteMapper;
import com.lyun.estate.biz.spec.fang.rest.entity.FangSummary;
import com.lyun.estate.biz.spec.fang.rest.service.FangService;
import com.lyun.estate.biz.spec.xiaoqu.rest.entity.XiaoQuSummary;
import com.lyun.estate.biz.spec.xiaoqu.rest.service.XiaoQuService;
import com.lyun.estate.biz.support.def.BizType;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.core.supports.context.RestContext;
import com.lyun.estate.core.supports.exceptions.EasyCodeException;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    private XiaoQuService xiaoQuService;


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

    public List<Favorite> getFavorite(Long targetId, DomainType domainType) {
        if (domainType == null) {
            throw new EstateException(PARAM_NULL, "domainType");
        }
        if (targetId == null) {
            throw new EstateException(PARAM_NULL, "targetId");
        }
        return favoriteMapper.getFavorites(targetId, domainType);
    }

    public List<Long> getFollowerIds(long targetId, DomainType domainType) {
        ExceptionUtil.checkNotNull("domainType", domainType);
        return favoriteMapper.getFollowerIds(targetId, domainType);
    }

    public PageList<FangSummary> getFavoriteFang(FavoriteType type, PageBounds pageBounds) {
        ExceptionUtil.checkIllegal(type == FavoriteType.FANG_SELL || type == FavoriteType.FANG_RENT, "关注类型", type);
        PageList<Favorite> favorites = favoriteMapper.getFavoritesByFollowerId(restContext.getUserId(),
                type,
                pageBounds);
        List<FangSummary> fangSummaries = new ArrayList<>();
        favorites.forEach(favorite -> fangSummaries.add(fangService.getSummary(favorite.getTargetId())));

        return new PageList<>(fangSummaries, favorites.getPaginator());
    }

    public PageList<XiaoQuSummary> getFavoriteXiaoQu(PageBounds pageBounds) {
        PageList<Favorite> favorites = favoriteMapper.getFavoritesByFollowerId(restContext.getUserId(),
                FavoriteType.XIAO_QU,
                pageBounds);
        List<XiaoQuSummary> xiaoQuSummaries = new ArrayList<>();
        favorites.forEach(favorite -> xiaoQuSummaries.add(xiaoQuService.getSummary(favorite.getTargetId())));
        return new PageList<>(xiaoQuSummaries, favorites.getPaginator());
    }
}
