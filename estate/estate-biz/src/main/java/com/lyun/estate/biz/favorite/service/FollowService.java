package com.lyun.estate.biz.favorite.service;

import com.lyun.estate.biz.favorite.entity.Follow;
import com.lyun.estate.biz.favorite.repository.FollowMapper;
import com.lyun.estate.biz.spec.common.DomainType;
import com.lyun.estate.core.supports.context.RestContext;
import com.lyun.estate.core.supports.exceptions.EasyCodeException;
import com.lyun.estate.core.supports.exceptions.EstateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.lyun.estate.core.supports.exceptions.ExCode.PARAM_NULL;

@Service
public class FollowService {

    @Autowired
    private RestContext restContext;

    @Autowired
    private FollowMapper followMapper;

    @Transactional
    public boolean createFollow(long targetId, DomainType domainType) {
        if (isFollow(targetId, domainType)) {
            return true;
        }
        long userId = restContext.getUserId();
        if (1 != followMapper.createFollow(targetId, domainType, userId)) {
            throw new EasyCodeException("favorite.error", "关注失败");
        } else {
            return true;
        }
    }

    @Transactional
    public boolean cancelFollow(long targetId, DomainType domainType) {
        if (!isFollow(targetId, domainType)) {
            return true;
        }
        long userId = restContext.getUserId();
        if (1 != followMapper.deleteFollow(targetId, domainType, userId)) {
            throw new EasyCodeException("cancel.favorite.error", "取消关注失败");
        } else {
            return true;
        }
    }

    public boolean isFollow(long targetId, DomainType domainType) {
        return null != followMapper.findFollow(targetId, domainType, restContext.getUserId());
    }

    public List<Follow> getFollowers(DomainType domainType, Long targetId) {
        if (domainType == null) {
            throw new EstateException(PARAM_NULL, "domainType");
        }
        if (targetId == null) {
            throw new EstateException(PARAM_NULL, "targetId");
        }
        return followMapper.getFollowers(domainType, targetId);
    }
}
