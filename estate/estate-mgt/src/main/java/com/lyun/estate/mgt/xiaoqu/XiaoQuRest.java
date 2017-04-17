package com.lyun.estate.mgt.xiaoqu;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.spec.xiaoqu.mgt.entity.MgtXiaoQuFilter;
import com.lyun.estate.biz.spec.xiaoqu.mgt.entity.MgtXiaoQuSummary;
import com.lyun.estate.biz.spec.xiaoqu.mgt.service.MgtXiaoQuService;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQu;
import com.lyun.estate.core.supports.pagebound.PageBoundsArgumentResolver;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Jeffrey on 2017-02-27.
 */
@RestController
@RequestMapping("api/xiao-qu")
public class XiaoQuRest {

    private MgtXiaoQuService mgtXiaoQuService;

    public XiaoQuRest(MgtXiaoQuService mgtXiaoQuService) {
        this.mgtXiaoQuService = mgtXiaoQuService;
    }

    @GetMapping("/{id}")
    public XiaoQu xiaoQu(@PathVariable("id") Long id) {
        return mgtXiaoQuService.findOne(id);
    }

    @GetMapping("/list")
    public PageList<MgtXiaoQuSummary> summary(@RequestParam Long cityId,
                                              @RequestParam(required = false) Long districtId,
                                              @RequestParam(required = false) Long subDistrictId,
                                              @RequestParam(required = false) Long xiaoQuId,
                                              @RequestHeader(PageBoundsArgumentResolver.PAGE_HEADER) PageBounds pageBounds) {
        MgtXiaoQuFilter filter = new MgtXiaoQuFilter()
                .setCityId(cityId)
                .setDistrictId(districtId)
                .setSubDistrictId(subDistrictId)
                .setXiaoQuId(xiaoQuId);

        return mgtXiaoQuService.list(filter, pageBounds);
    }

}
