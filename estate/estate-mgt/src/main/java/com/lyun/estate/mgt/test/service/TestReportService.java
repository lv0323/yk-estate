package com.lyun.estate.mgt.test.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.lyun.estate.biz.report.engine.ReportEngine;
import com.lyun.estate.core.supports.exceptions.EstateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jeffrey on 2017-04-17.
 */
@Service
public class TestReportService {
    private Logger logger = LoggerFactory.getLogger(TestReportService.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ReportEngine reportEngine;

    @PostConstruct
    public void init() {
        this.mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    public String getXiaoQuReportQuery(Date startTime, Date endTime, String domainType, Long offset, Long limit) {
        logger.info("query report [" + "getXiaoQu" + "] start...");
        Map<String, Object> param = assembleDomainTypeParam(startTime, endTime, domainType);
        param.put("offset", offset);
        param.put("limit", limit);
        try {
            List<Map<String, Object>> mapList = reportEngine.reportQuery("getXiaoQu", param);
            return mapper.writeValueAsString(mapList);
        } catch (Exception e) {
            logger.error("query reportQuery [{}] have error : [{}]", "getXiaoQu", e);
            throw new EstateException();
        } finally {
            logger.info("query report [" + "getXiaoQu" + "] end!");
        }
    }

    public void getXiaoQuReportExport(Date startTime, Date endTime, String domainType, HttpServletResponse response) {
        logger.info("export report [" + "getXiaoQu" + "] start...");
        Map<String, Object> param = assembleDomainTypeParam(startTime, endTime, domainType);
        try {
            reportEngine.reportExport("getXiaoQu", param, response);
//            auditService.addAudit(adminSession.getId(), AuditEntity.AuditTopic.REPORT, AuditEntity.AuditSubject.QUERY, "getXiaoQu", "", "param:" + param.toString());
        } catch (Exception e) {
            logger.error("export reportQuery [{}] have error : [{}]", "deposit", e);
            throw new EstateException();
        } finally {
            logger.info("query report [" + "getXiaoQu" + "] end!");
        }
    }

    private Map<String, Object> assembleDomainTypeParam(Date startTime, Date endTime, String domainType) {
        Map<String, Object> param = new HashMap<String, Object>();
        if (startTime != null) {
            param.put("startTime", startTime);
        } else {
            param.put("startTime", null);
        }
        if (endTime != null) {
            param.put("endTime", new Date(endTime.getTime() + 24 * 60 * 60 * 1000));
        } else {
            param.put("endTime", null);
        }
        if (!StringUtils.isEmpty(domainType)) {
            param.put("domainType", domainType);
        } else {
            param.put("domainType", null);
        }
        return param;
    }
}
