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
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ReportEngine {
    private final static Logger logger = LoggerFactory.getLogger(ReportEngine.class);
    static final String COMMA_SPLIT = ",";
    static final String LINE_SPLIT = "\r\n";
    static final String DEFAULT = "DEFAULT";

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
    public List<Map<String, Object>> reportQuery(String reportName, Map param) throws SQLException, JsonProcessingException {
        return reportQuery(reportName, DEFAULT, param);
    }


    public List<Map<String, Object>> reportQuery(String reportName, String region, Map param) throws SQLException, JsonProcessingException {
        Assert.notNull(reportName, "report name is null");

        ReportInfo reportInfo = reportDataSourceUtils.getReportInfo(reportName, region);
        Assert.notNull(reportInfo, "cant not found report for [" + region + ":" + reportName + "]");

        String sql = assembleSql(reportInfo.getSql(), param);
        List<ReportHeader> headerList = reportInfo.getHeaderList();
        ReportQueryResultHandler resultHandler = new ReportQueryResultHandler(headerList, resultObjectHandler);
        reportMapper.report(sql, resultHandler);
        return resultHandler.getResultList();
    }

    public void reportQuery(String reportName, Map param, OutputStream os) throws SQLException, UnsupportedEncodingException {
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
    public void reportQuery(String reportName, String region, Map param, OutputStream os) throws SQLException, UnsupportedEncodingException {
        Assert.notNull(reportName, "report name is null");
        Assert.notNull(os, "OutputStream is null");

        ReportInfo reportInfo = reportDataSourceUtils.getReportInfo(reportName, region);
        Assert.notNull(reportInfo, "cant find report by name [" + region + ":" + reportName + "]");

        String sql = assembleSql(reportInfo.getSql(), param);
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(os, "UTF-8"), true);
        printWriter.write("[");
        printWriter.flush();

        List<ReportHeader> headerList = reportInfo.getHeaderList();

        ReportQueryResultHandler resultHandler = new ReportQueryResultHandler(printWriter, headerList, resultObjectHandler);
        reportMapper.report(sql, resultHandler);

        printWriter.write("]");
        printWriter.flush();
    }

    public <T> void reportExport(String reportName, Map param, HttpServletResponse response) throws SQLException, IOException, ReflectiveOperationException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\""
                + URLEncoder.encode(reportName, "UTF-8") + ".csv\"");
        reportExport(reportName, param, response.getOutputStream());
    }

    public <T> void reportExport(String reportName, Map param, OutputStream os) throws SQLException, UnsupportedEncodingException, ReflectiveOperationException {
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
    public <T> void reportExport(String reportName, String region, Map param, OutputStream os) throws SQLException, UnsupportedEncodingException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
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

        ReportExportResultHandler resultHandler = new ReportExportResultHandler(printWriter, headerList, this.resultObjectHandler);
        reportMapper.report(sql, resultHandler);

        printWriter.flush();
    }

    public List<String> reportExport(String reportName, Map param) throws SQLException, UnsupportedEncodingException, ReflectiveOperationException {
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
    public List<String> reportExport(String reportName, String region, Map param) throws SQLException, UnsupportedEncodingException, ReflectiveOperationException {
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
        Long limit = param.get("limit") != null ? (Long) param.get("limit") : null;
        Long offset = param.get("offset") != null ? (Long) param.get("offset") : null;
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
            if (Integer.class.isInstance(entry.getValue()) || Long.class.isInstance(entry.getValue()) || Float.class.isInstance(entry.getValue()) || BigDecimal.class.isInstance(entry.getValue())) {
                sql = matcher.replaceAll(entry.getValue().toString());
            } else if (Date.class.isInstance(entry.getValue())){
                Date value = (Date) entry.getValue();
                DateTime dateTime = new DateTime(value);
                sql = matcher.replaceAll("'" + dateTime.toString() + "'");
            }else {
                sql = matcher.replaceAll("'" + entry.getValue().toString() + "'");
            }
        }
        Pattern pattern = Pattern.compile("(#\\{\\w*?\\})");
        Matcher matcher = pattern.matcher(sql);
        sql = matcher.replaceAll("null");

        return sql;
    }

//////    private ResultSet executeSql(Connection connection, String sql) throws SQLException {
//////        PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//////        statement.setFetchSize(50);
//////        return statement.executeQuery();
//////    }
//////
//////    private <T> String resultSetHandler(ResultSet rs, Class<T> classType) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, JsonProcessingException {
//////        List<String> columns = getResultRestColumnList(rs);
//////        Field[] fields = classType.getDeclaredFields();
//////        Map<String, Field> fieldMap = Arrays.stream(fields).collect(Collectors.toMap(k -> k.getName(), v -> v));
//////
//////        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
//////        Map<String, Object> resultMap = null;
//////        while (rs.next()) {
//////            resultMap = new LinkedHashMap<String, Object>();
//////            for (String columnName : columns) {
//////                Field field = fieldMap.get(reportUtils.camelName(columnName));
//////                if (field == null) {
//////                    resultMap.put(reportUtils.camelOrUnderscoreName(columnName), "");
//////
//////                } else {
//////                    Class<?> fieldClass = Class.forName(field.getGenericType().getTypeName());
//////                    Object value = getResultFromResultSet(rs, field.getName(), fieldClass);
////////                    if (fieldClass == Date.class) {
////////                        value = ((Date) value).getTime();
////////                    }
//////                    resultMap.put(field.getName(), value);
//////                }
//////            }
//////            resultList.add(resultMap);
//////        }
//////        return mapper.writeValueAsString(resultList);
//////    }
//////
//////    private <T> String resultSetHandler(ResultSet rs) throws SQLException, JsonProcessingException {
//////        List<String> columns = getResultRestColumnList(rs);
//////        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
//////        Map<String, Object> resultMap = null;
//////        while (rs.next()) {
//////            resultMap = new LinkedHashMap<String, Object>();
//////            for (String columnName : columns) {
//////                String value = rs.getString(columnName);
//////                resultMap.put(reportUtils.camelOrUnderscoreName(columnName), value);
//////            }
//////            resultList.add(resultMap);
//////        }
//////        return mapper.writeValueAsString(resultList);
//////    }
//////
//////    private <T> void resultSetReportHandler(ResultSet rs, OutputStream os, Class<T> classType) throws SQLException, UnsupportedEncodingException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
//////        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(os, "UTF-8"), true);
//////        printWriter.flush();
//////
//////        List<String> columns = getResultRestColumnList(rs);
//////        Field[] fields = classType.getDeclaredFields();
//////        Map<String, Field> fieldMap = Arrays.stream(fields).collect(Collectors.toMap(k -> k.getName(), v -> v));
//////
//////        printWriter.write("[");
//////        while (rs.next()) {
//////            StringBuilder sb = new StringBuilder();
//////            if (rs.isFirst()) {
//////                sb.append("{");
//////            } else {
//////                sb.append(",{");
//////            }
//////
//////            for (String columnName : columns) {
//////                Field field = fieldMap.get(reportUtils.camelName(columnName));
//////                if (field == null) {
//////                    sb.append(reportUtils.camelName(columnName)).append(":").append("").append(",");
//////                } else {
//////                    Class<?> fieldClass = Class.forName(field.getGenericType().getTypeName());
//////                    Object value = getResultFromResultSet(rs, field.getName(), fieldClass);
////////                    if (fieldClass == Date.class) {
////////                        value = ((Date) value).getTime();
////////                    }
//////                    sb.append("\"").append(field.getName()).append("\"").append(":").append("\"").append(value).append("\"").append(",");
//////                }
//////            }
//////            sb.deleteCharAt(sb.length() - 1);
//////            sb.append("}");
//////            printWriter.write(sb.toString());
//////        }
//////        printWriter.write("]");
//////        printWriter.flush();
//////    }
//////
//////    private void resultSetReportHandler(ResultSet rs, OutputStream os) throws SQLException, UnsupportedEncodingException {
//////        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(os, "UTF-8"), true);
//////        printWriter.flush();
//////
//////        List<String> columns = getResultRestColumnList(rs);
//////
//////        printWriter.write("[");
//////        while (rs.next()) {
//////            StringBuilder sb = new StringBuilder();
//////            if (rs.isFirst()) {
//////                sb.append("{");
//////            } else {
//////                sb.append(",{");
//////            }
//////            for (String columnName : columns) {
//////                String value = rs.getString(columnName);
//////                sb.append("\"").append(reportUtils.camelOrUnderscoreName(columnName)).append("\"").append(":").append("\"").append(value).append("\"").append(",");
//////            }
//////            sb.deleteCharAt(sb.length() - 1);
//////            sb.append("}");
//////            printWriter.write(sb.toString());
//////        }
//////        printWriter.write("]");
//////        printWriter.flush();
//////    }
//////
//////    private <T> void resultSetExportHandler(ResultSet rs, ReportInfo reportInfo, OutputStream os, Class<T> classType) throws SQLException, UnsupportedEncodingException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
//////        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(os, "UTF-8"), true);
//////
////////        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(os, "UTF-8"), true);
//////        printWriter.flush();
//////
//////        List<String> headerList = getHeaderList(reportInfo.getId(), reportInfo.getRegion());
//////        if (!CollectionUtils.isEmpty(headerList)) {
//////            String header = String.join(COMMA_SPLIT, headerList);
//////            printWriter.write(header);
//////            printWriter.write(LINE_SPLIT);
//////        }
//////
//////        List<String> columns = getResultRestColumnList(rs);
//////        Field[] fields = classType.getDeclaredFields();
//////        Map<String, Field> fieldMap = Arrays.stream(fields).collect(Collectors.toMap(k -> k.getName(), v -> v));
//////
//////        List<String> tempLineList = null;
//////        while (rs.next()) {
//////            tempLineList = new ArrayList<String>();
//////            for (String columnName : columns) {
//////                Field field = fieldMap.get(reportUtils.camelName(columnName));
//////                if (field == null) {
//////                    tempLineList.add(formatCsvString(null));
//////                } else {
//////                    Class<?> fieldClass = Class.forName(field.getGenericType().getTypeName());
//////                    Object value = getResultFromResultSet(rs, field.getName(), fieldClass);
//////                    if (fieldClass == Date.class) {
//////                        value = reportUtils.format((Date) value);
//////                    }
//////                    tempLineList.add(formatCsvString(value));
//////                }
//////            }
//////            printWriter.write(String.join(COMMA_SPLIT, tempLineList));
//////            printWriter.write(LINE_SPLIT);
//////        }
//////        printWriter.flush();
//////    }
//////
//////    private <T> void resultSetExportHandler(ResultSet rs, ReportInfo reportInfo, OutputStream os) throws SQLException, UnsupportedEncodingException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
//////        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(os, "UTF-8"), true);
//////
//////        printWriter.flush();
//////
//////        List<String> headerList = getHeaderList(reportInfo.getId(), reportInfo.getRegion());
//////        if (!CollectionUtils.isEmpty(headerList)) {
//////            String header = String.join(COMMA_SPLIT, headerList);
//////            printWriter.write(header);
//////            printWriter.write(LINE_SPLIT);
//////        }
//////
//////        List<String> columns = getResultRestColumnList(rs);
//////        List<String> tempLineList = null;
//////        while (rs.next()) {
//////            tempLineList = new ArrayList<String>();
//////            for (String columnName : columns) {
//////                tempLineList.add(formatCsvString(rs.getString(columnName)));
//////            }
//////            printWriter.write(String.join(COMMA_SPLIT, tempLineList));
//////            printWriter.write(LINE_SPLIT);
//////        }
//////        printWriter.flush();
//////    }
////
////    private List<String> getResultRestColumnList(ResultSet rs) throws SQLException {
////        ResultSetMetaData metaData = rs.getMetaData();
////        int columnCount = metaData.getColumnCount();
////        List<String> columns = new ArrayList<String>();
////        for (int i = 1; i <= columnCount; i++) {
////            String columnName = metaData.getColumnName(i);
////            columns.add(columnName);
////        }
////        return columns;
////    }
////
////    private <T> Object getResultFromResultSet(ResultSet resultSet, String name, Class<T> classType) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
////        String columnName = getActColumnNameFromResultSet(resultSet, name);
////        if (classType.isEnum()) {
////            Method method = classType.getMethod("valueOf", String.class);
////            Object enumInstance = method.invoke(null, resultSet.getString(columnName));
////            try {
////                Method getLabelMethod = classType.getMethod("getLabel");
////                return getLabelMethod.invoke(enumInstance);
////            } catch (NoSuchMethodException e) {
////                return resultSet.getString(columnName);
////            }
////        } else if (classType == Date.class || classType == Timestamp.class) {
////            try {
////                return resultSet.getTimestamp(columnName);
////            } catch (SQLException e) {
////                return resultSet.getString(columnName);
////            }
////        } else {
////            try {
////                return resultSet.getObject(columnName, classType);
////            } catch (SQLException e) {
////                return resultSet.getString(columnName);
////            }
////        }
////    }
//
//    private String getActColumnNameFromResultSet(ResultSet resultSet, String name) throws SQLException {
//        try {
//            String columnName = reportUtils.camelOrUnderscoreName(name);
//            if (resultSet.findColumn(columnName) >= 0) {
//                return columnName;
//            }
//            throw new SQLException("cant find column[" + name + "]");
//        } catch (SQLException e) {
//            if (resultSet.findColumn(name) >= 0) {
//                return name;
//            }
//            throw e;
//        }
//    }
//
//    private String formatCsvString(Object result) {
//        if (StringUtils.isEmpty(result)) {
//            return "=\"\"";
//        } else {
//            return "=\"" + result.toString() + "\"";
//        }
//    }
}
