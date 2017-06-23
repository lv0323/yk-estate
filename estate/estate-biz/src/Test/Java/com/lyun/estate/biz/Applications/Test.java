package com.lyun.estate.biz.Applications;

import com.lyun.estate.biz.application.entity.CommonApplicationEntity;
import com.lyun.estate.biz.application.CommonApplicationService;
import com.lyun.estate.biz.config.BizConfig;
import com.lyun.estate.core.config.CoreConfig;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BizConfig.class, CoreConfig.class})

public class Test {

    @Autowired
    private CommonApplicationService commonApplicationService;


    @org.junit.Test
    public void test() {
        commonApplicationService.create(CommonApplicationEntity.Type.UN_PUBLIC_HOUSE, 49, "xiaoming test only", 5028);
        commonApplicationService.approve(1, 49, "xiaoming test only");
    }
}
