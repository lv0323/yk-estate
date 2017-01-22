package com.lyun.estate.biz.fang.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.lyun.estate.biz.housedict.service.HouseService;
import com.lyun.estate.biz.keyword.entity.KeywordBean;
import com.lyun.estate.biz.keyword.service.KeywordService;
import com.lyun.estate.biz.spec.common.DomainType;
import com.lyun.estate.biz.spec.fang.entity.FangDetail;
import com.lyun.estate.biz.spec.fang.entity.FangFilter;
import com.lyun.estate.biz.spec.fang.entity.FangSummary;
import com.lyun.estate.biz.spec.fang.service.FangService;
import com.lyun.estate.biz.spec.xiaoqu.def.XQSummaryOrder;
import com.lyun.estate.biz.spec.xiaoqu.entity.KeywordResp;
import com.lyun.estate.biz.spec.xiaoqu.entity.XiaoQuDetail;
import com.lyun.estate.biz.spec.xiaoqu.service.XiaoQuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeffrey on 2017-01-20.
 */
@Service
public class FangServiceImpl implements FangService {

    @Autowired
    private KeywordService keywordService;

    @Autowired
    private HouseService houseService;

    @Autowired
    private XiaoQuService xiaoQuService;

    @Override
    public List<KeywordResp> keywords(Long cityId, String keyword) {
        List<KeywordResp> result = new ArrayList<>();
        if (Strings.isNullOrEmpty(keyword)) {
            return result;
        }
        List<KeywordBean> keywordBeans = keywordService.findContain(keyword, cityId,
                Lists.newArrayList(DomainType.DISTRICT,
                        DomainType.SUB_DISTRICT,
                        DomainType.LINE,
                        DomainType.STATION,
                        DomainType.XIAO_QU), 10);

        keywordBeans.forEach(keywordBean -> {
            KeywordResp resp = new KeywordResp();
            resp.setType(keywordBean.getDomainType());
            resp.setResp(keywordBean.getName());

            StringBuilder noteBuilder = new StringBuilder();
            if (!Strings.isNullOrEmpty(keywordBean.getAlias())) {
                noteBuilder.append("(").append(keywordBean.getAlias()).append(") ");
            }

            if (keywordBean.getDomainType() == DomainType.SUB_DISTRICT) {
                noteBuilder.append(houseService.findPrimaryDistrict(keywordBean.getId()).getName());
            } else if (keywordBean.getDomainType() == DomainType.XIAO_QU) {
                XiaoQuDetail detail = xiaoQuService.getDetail(keywordBean.getId());
                noteBuilder.append(detail.getDistrict()).append(" ")
                        .append(detail.getSubDistrict());
                resp.setNote(noteBuilder.toString());
            } else if (keywordBean.getDomainType() == DomainType.LINE) {
                noteBuilder.append("地铁线");
            } else if (keywordBean.getDomainType() == DomainType.STATION) {
                noteBuilder.append("地铁站");
            }
            resp.setNote(noteBuilder.toString().trim());
            result.add(resp);
        });
        return result;
    }

    @Override
    public PageList<FangSummary> findFangSummaryByKeyword(FangFilter filter, XQSummaryOrder order,
                                                          PageBounds pageBounds) {
        return null;
    }

    @Override
    public FangDetail getDetail(Long id) {
        return null;
    }

    @Override
    public FangSummary getSummary(Long id) {
        //TODO 实现
        return null;
    }

}
