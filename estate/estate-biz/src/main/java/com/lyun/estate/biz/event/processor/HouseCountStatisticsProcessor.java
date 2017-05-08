package com.lyun.estate.biz.event.processor;

import com.lyun.estate.biz.event.entity.EventProcess;
import com.lyun.estate.biz.event.service.EventService;
import com.lyun.estate.biz.housedict.service.HouseDictService;
import com.lyun.estate.biz.spec.xiaoqu.rest.entity.XiaoQuSummary;
import com.lyun.estate.biz.spec.xiaoqu.rest.service.XiaoQuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jeffrey on 2017-03-15.
 */
@Service
public class HouseCountStatisticsProcessor implements EventProcessor {

    public static final String PROCESSOR_NAME = "statisticProcessor";

    @Autowired
    private EventService eventService;

    @Autowired
    private XiaoQuService xiaoQuService;

    @Autowired
    private HouseDictService houseDictService;


    @Override
    @Transactional
    public EventProcess process(EventProcess process) {
        eventService.lockProcess(process.getId());
        //更新小区所在区域的房源数
        long xiaoQuId = process.getDomainId();

        XiaoQuSummary xiaoQuSummary = xiaoQuService.getSummary(xiaoQuId);

        houseDictService.houseCountForSubDistrictAndDistrict(xiaoQuSummary.getSubDistrictId());

        //更新包含该区域的的板块的房源数
        return eventService.closeProcess(process.getId());
    }


    @Override
    public String processorName() {
        return PROCESSOR_NAME;
    }
}
