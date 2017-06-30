package com.lyun.estate.biz.xiaoqu;

import com.lyun.estate.biz.config.BizConfig;
import com.lyun.estate.biz.spec.xiaoqu.mgt.service.MgtXiaoQuService;
import com.lyun.estate.biz.xiaoqu.entity.CommunityEntity;
import com.lyun.estate.core.config.CoreConfig;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BizConfig.class, CoreConfig.class})

public class XiaoquTest {
    @Autowired
    private MgtXiaoQuService mgtXiaoQuService;


    @Ignore
    @Test
    public void test() {
        mgtXiaoQuService.createXiaoQu("小明测试", "测试", 1, 80, BigDecimal.ZERO, BigDecimal.ZERO, "");

        CommunityEntity communityEntity = new CommunityEntity(){{
            setId(11002);
            setBuildedYear(2017);
            setBuildings(16);
            setHouses(1024);
            setStructureType(5);
            setDevelopYear(2016);
            setPropertyFee("很贵");
            setPropertyCompany("你这样我要报警了");
            setPropertyCompanyPhone("110");
            setDevelopers("开发商不怕报警");
            setParkingSpace("2048");
            setParkingFee("100/h");
            setParkingRate("100%");
            setPropertyFee("10/m*m");
            setContainerRate("100");
            setGreenRate("90%");
            setAddress("天安门前");
        }};

        mgtXiaoQuService.updateXiaoQu(communityEntity);
    }
}
