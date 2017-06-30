package com.lyun.estate.biz.applications;

import com.lyun.estate.biz.application.entity.CommonApplicationEntity;
import com.lyun.estate.biz.application.CommonApplicationService;
import com.lyun.estate.biz.config.BizConfig;
import com.lyun.estate.core.config.CoreConfig;
import org.junit.Ignore;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BizConfig.class, CoreConfig.class})

public class Test {

    @Autowired
    private CommonApplicationService commonApplicationService;

    @Ignore
    @org.junit.Test
    public void test() {
        CommonApplicationEntity commonApplicationEntity = commonApplicationService.create(CommonApplicationEntity.Type.PUBLIC_HOUSE, 49, "xiaoming test only", 5028);
        commonApplicationService.approve(commonApplicationEntity.getId(), 49, "xiaoming test only", false);
    }
}
