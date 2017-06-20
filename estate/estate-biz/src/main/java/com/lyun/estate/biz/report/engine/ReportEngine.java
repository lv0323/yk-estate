package com.lyun.estate.biz.report.engine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.lyun.estate.biz.report.repo.ReportMapper;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ReportEngine {
    static final String COMMA_SPLIT = ",";
    static final String LINE_SPLIT = "\r\n";
    static final String DEFAULT = "DEFAULT";
    private final static Logger logger = LoggerFactory.getLogger(ReportEngine.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ReportDataSourceUtils reportDataSourceUtils;

    @Autowired
    private ReportUtils reportUtils;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ReportMapper reportMapper;


    @Autowired
    private ResultObjectHandler resultObjectHandler;

    @PostConstruct
    public void init() {
        this.mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    public List<String> getHeaderList(String reportName, String region) {
        ReportInfo reportInfo = reportDataSourceUtils.getReportInfo(reportName, region);
        Assert.notNull(reportInfo, "cant find report by name [" + region + ":" + reportName + "]");
        return reportInfo.getHeaderList().stream().map(ReportHeader::getName).collect(Collectors.toList());
    }

    public List<String> getHeaderList(String reportName) {
        return getHeaderList(reportName, DEFAULT);
    }

    public boolean updateReportInfo(String reportName, String region, String sql, List<ReportHeader> headerList) {
        ReportInfo reportInfo = new ReportInfo();
        reportInfo.setId(reportName);
        reportInfo.setRegion(region);
        reportInfo.setSql(sql);
        reportInfo.setHeaderList(headerList);
        return reportDataSourceUtils.updateReportInfo(reportName, region, reportInfo);
    }


    /**
     * 小流量数据查询
     *
     * @param reportName
     * @param param
     * @return
     * @throws SQLException
     */
    public List<Map<String, Object>> reportQuery(String reportName,
                                                 Map param) throws SQLException, JsonProcessingException {
        return reportQuery(reportName, DEFAULT, param);
    }


    public List<Map<String, Object>> reportQuery(String reportName, String region,
                                                 Map param) throws SQLException, JsonProcessingException {
        Assert.notNull(reportName, "report name is null");

        ReportInfo reportInfo = reportDataSourceUtils.getReportInfo(reportName, region);
        Assert.notNull(reportInfo, "cant not found report for [" + region + ":" + reportName + "]");

        String sql = assembleSql(reportInfo.getSql(), param);
        List<ReportHeader> headerList = reportInfo.getHeaderList();
        ReportQueryResultHandler resultHandler = new ReportQueryResultHandler(headerList, resultObjectHandler);
        reportMapper.report(sql, resultHandler);
        return resultHandler.getResultList();
    }

    public void reportQuery(String reportName, Map param,
                            OutputStream os) throws SQLException, UnsupportedEncodingException {
        reportQuery(reportName, DEFAULT, param, os);
    }


    /**
     * 大流量数据查询
     *
     * @param reportName
     * @param region
     * @param param
     * @param os
     * @throws SQLException
     * @throws UnsupportedEncodingException
     */
    public void reportQuery(String reportName, String region, Map param,
                            OutputStream os) throws SQLException, UnsupportedEncodingException {
        Assert.notNull(reportName, "report name is null");
        Assert.notNull(os, "OutputStream is null");

        ReportInfo reportInfo = reportDataSourceUtils.getReportInfo(reportName, region);
        Assert.notNull(reportInfo, "cant find report by name [" + region + ":" + reportName + "]");

        String sql = assembleSql(reportInfo.getSql(), param);
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(os, "UTF-8"), true);
        printWriter.write("[");
        printWriter.flush();

        List<ReportHeader> headerList = reportInfo.getHeaderList();

        ReportQueryResultHandler resultHandler = new ReportQueryResultHandler(printWriter,
                headerList,
                resultObjectHandler);
        reportMapper.report(sql, resultHandler);

        printWriter.write("]");
        printWriter.flush();
    }

    public <T> void reportExport(String reportName, Map param,
                                 HttpServletResponse response) throws SQLException, IOException, ReflectiveOperationException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\""
                + URLEncoder.encode(reportName, "UTF-8") + ".csv\"");
        reportExport(reportName, param, response.getOutputStream());
    }

    public <T> void reportExport(String reportName, Map param,
                                 OutputStream os) throws SQLException, UnsupportedEncodingException, ReflectiveOperationException {
        reportExport(reportName, DEFAULT, param, os);
    }


    /**
     * 数据导出，默认采用大流量方式
     *
     * @param reportName
     * @param region
     * @param param
     * @param os
     * @throws SQLException
     * @throws UnsupportedEncodingException
     */
    public <T> void reportExport(String reportName, String region, Map param,
                                 OutputStream os) throws SQLException, UnsupportedEncodingException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        Assert.notNull(reportName, "report name is null");
        Assert.notNull(os, "OutputStream is null");

        ReportInfo reportInfo = reportDataSourceUtils.getReportInfo(reportName, region);
        Assert.notNull(reportInfo, "cant find report by name [" + region + ":" + reportName + "]");

        String sql = assembleSql(reportInfo.getSql(), param);
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(os, "UTF-8"), true);
        printWriter.flush();

        List<String> headerNameList = getHeaderList(reportName, region);
        if (!CollectionUtils.isEmpty(headerNameList)) {
            String header = String.join(COMMA_SPLIT, headerNameList);
            printWriter.write(header);
            printWriter.write(LINE_SPLIT);
        }
        printWriter.flush();

        List<ReportHeader> headerList = reportInfo.getHeaderList();

        ReportExportResultHandler resultHandler = new ReportExportResultHandler(printWriter,
                headerList,
                this.resultObjectHandler);
        reportMapper.report(sql, resultHandler);

        printWriter.flush();
    }

    public List<String> reportExport(String reportName,
                                     Map param) throws SQLException, UnsupportedEncodingException, ReflectiveOperationException {
        return reportExport(reportName, DEFAULT, param);
    }


    /**
     * 数据导出，默认采用大流量方式
     *
     * @param reportName
     * @param region
     * @param param
     * @throws SQLException
     * @throws UnsupportedEncodingException
     */
    public List<String> reportExport(String reportName, String region,
                                     Map param) throws SQLException, UnsupportedEncodingException, ReflectiveOperationException {
        Assert.notNull(reportName, "report name is null");

        ReportInfo reportInfo = reportDataSourceUtils.getReportInfo(reportName, region);
        Assert.notNull(reportInfo, "cant find report by name [" + region + ":" + reportName + "]");

        String sql = assembleSql(reportInfo.getSql(), param);
        List<ReportHeader> headerList = reportInfo.getHeaderList();
        ReportExportResultHandler resultHandler = new ReportExportResultHandler(headerList, this.resultObjectHandler);
        reportMapper.report(sql, resultHandler);

        return resultHandler.getResultList();
    }


    @SuppressWarnings("unchecked")
    private String assembleSql(String sql, Map param) {
        Assert.hasLength(sql, "SQL is null");
        if (CollectionUtils.isEmpty(param)) {
            return sql;
        }
        Integer limit = param.get("limit") != null ? (Integer) param.get("limit") : null;
        Integer offset = param.get("offset") != null ? (Integer) param.get("offset") : null;
        StringBuilder sb = new StringBuilder("\n");
        if (offset != null) {
            sb.append(" offset ").append(offset);
        }
        if (limit != null) {
            sb.append(" limit ").append(limit);
        }
        sql = sql + sb.toString();
        Set<Map.Entry<String, Object>> entries = param.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            if (StringUtils.isEmpty(entry.getValue()) || StringUtils.isEmpty(entry.getValue().toString().trim())) {
                continue;
            }
            String regex = "(#\\{" + entry.getKey() + "\\})";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(sql);
            if (Integer.class.isInstance(entry.getValue()) || Long.class.isInstance(entry.getValue()) || Float.class.isInstance(
                    entry.getValue()) || BigDecimal.class.isInstance(entry.getValue())) {
                sql = matcher.replaceAll(entry.getValue().toString());
            } else if (Date.class.isInstance(entry.getValue())) {
                Date value = (Date) entry.getValue();
                DateTime dateTime = new DateTime(value);
                sql = matcher.replaceAll("'" + dateTime.toString() + "'");
            } else {
                sql = matcher.replaceAll("'" + entry.getValue().toString() + "'");
            }
        }
        Pattern pattern = Pattern.compile("(#\\{\\w*?\\})");
        Matcher matcher = pattern.matcher(sql);
        sql = matcher.replaceAll("null");

        return sql;
    }
}
