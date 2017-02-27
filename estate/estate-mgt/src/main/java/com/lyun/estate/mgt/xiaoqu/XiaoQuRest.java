package com.lyun.estate.mgt.xiaoqu;

import com.lyun.estate.biz.spec.xiaoqu.mgt.service.MgtXiaoQuService;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQu;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jeffrey on 2017-02-27.
 */
@RestController("api/xiao-qu")
public class XiaoQuRest {

    private MgtXiaoQuService mgtXiaoQuService;

    public XiaoQuRest(MgtXiaoQuService mgtXiaoQuService) {
        this.mgtXiaoQuService = mgtXiaoQuService;
    }

    @GetMapping("/{id}")
    public XiaoQu xiaoQu(@PathVariable Long id) {
        return mgtXiaoQuService.findOne(id);
    }
}
