package com.lyun.estate.biz.report.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by siminglun on 2017/1/25.
 */
@Service
public class ReportEngine {
    private static final String COMMA_SPLIT = ",";
    private static final String LINE_SPLIT = "\r\n";

    @Autowired(required = false)
    private ReportDataSourceUtils reportDataSourceUtils;

    @Autowired
    private ReportUtils reportUtils;

    @Autowired
    private DataSource dataSource;

    public List<String> getHeaderList(String reportName, String region) {
        ReportInfo reportInfo = reportDataSourceUtils.getReportInfo(reportName, region);
        Assert.notNull(reportInfo, "未找到[" + region + ":" + reportName + "]报表信息");
        return reportInfo.getReportHeader();
    }

    public boolean updateReportInfo(String reportName, String region, String sql, List<String> headerList) {
        ReportInfo reportInfo = new ReportInfo();
        reportInfo.setId(reportName);
        reportInfo.setRegion(region);
        reportInfo.setSql(sql);
        reportInfo.setReportHeader(headerList);
        return reportDataSourceUtils.updateReportInfo(reportName, region, reportInfo);
    }


    /**
     * 小流量数据查询
     * @param reportName
     * @param region
     * @param param
     * @return
     * @throws SQLException
     */
    public String report(String reportName, String region, Map param) throws SQLException {
        Assert.notNull(reportName);

        ReportInfo reportInfo = reportDataSourceUtils.getReportInfo(reportName, region);
        Assert.notNull(reportInfo, "未找到[" + region + ":" + reportName + "]报表信息");

        String sql = assembleSql(reportInfo.getSql(), param);
        ResultSet rs = executeSql(sql);
        return resultSetHandler(rs);
    }

    /**
     * 小流量数据查询
     * @param reportName
     * @param region
     * @param param
     * @return
     * @throws SQLException
     */
    public <T> String report(String reportName, String region, Map param, Class<T> resultClass) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        Assert.notNull(reportName);

        ReportInfo reportInfo = reportDataSourceUtils.getReportInfo(reportName, region);
        Assert.notNull(reportInfo, "未找到[" + region + ":" + reportName + "]报表信息");

        String sql = assembleSql(reportInfo.getSql(), param);
        ResultSet rs = executeSql(sql);
        return resultSetHandler(rs, resultClass);
    }

    /**
     * 大流量数据查询
     * @param reportName
     * @param region
     * @param param
     * @param os
     * @throws SQLException
     * @throws UnsupportedEncodingException
     */
    public void report(String reportName, String region, Map param, OutputStream os) throws SQLException, UnsupportedEncodingException {
        Assert.notNull(reportName);
        Assert.notNull(os);

        ReportInfo reportInfo = reportDataSourceUtils.getReportInfo(reportName, region);
        Assert.notNull(reportInfo, "未找到[" + region + ":" + reportName + "]报表信息");

        String sql = assembleSql(reportInfo.getSql(), param);
        ResultSet rs = executeSql(sql);
        resultSetReportHandler(rs, os);
    }

    /**
     * 大流量数据查询
     * @param reportName
     * @param region
     * @param param
     * @param os
     * @throws SQLException
     * @throws UnsupportedEncodingException
     */
    public <T> void report(String reportName, String region, Map param, OutputStream os, Class<T> classType) throws SQLException, UnsupportedEncodingException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        Assert.notNull(reportName);
        Assert.notNull(os);

        ReportInfo reportInfo = reportDataSourceUtils.getReportInfo(reportName, region);
        Assert.notNull(reportInfo, "未找到[" + region + ":" + reportName + "]报表信息");

        String sql = assembleSql(reportInfo.getSql(), param);
        ResultSet rs = executeSql(sql);
        resultSetReportHandler(rs, os, classType);
    }

    /**
     * 数据导出，默认采用大流量方式
     * @param reportName
     * @param region
     * @param param
     * @param os
     * @throws SQLException
     * @throws UnsupportedEncodingException
     */
    public <T> void exportCsv(String reportName, String region, Map param, OutputStream os, Class<T> classType) throws SQLException, UnsupportedEncodingException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        Assert.notNull(reportName);
        Assert.notNull(os);

        ReportInfo reportInfo = reportDataSourceUtils.getReportInfo(reportName, region);
        Assert.notNull(reportInfo, "未找到[" + region + ":" + reportName + "]报表信息");

        String sql = assembleSql(reportInfo.getSql(), param);
        ResultSet rs = executeSql(sql);
        resultSetExportHandler(rs, reportInfo, os, classType);
    }


    private String assembleSql(String sql, Map param) {
        Assert.hasLength(sql, "SQL不能为空");
        if (CollectionUtils.isEmpty(param)) {
            return sql;
        }
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
            } else {
                sql = matcher.replaceAll("'" + entry.getValue().toString() + "'");
            }
        }
        Pattern pattern = Pattern.compile("(#\\{\\w*?\\})");
        Matcher matcher = pattern.matcher(sql);
        sql = matcher.replaceAll("null");
        return sql;
    }

    private ResultSet executeSql(String sql) throws SQLException {
        Connection connection = DataSourceUtils.getConnection(dataSource);
        PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        statement.setFetchSize(50);
        return statement.executeQuery();
    }

    private <T> String resultSetHandler(ResultSet rs, Class<T> classType) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        List<String> columns = getResultRestColumnList(rs);
        Field[] fields = classType.getDeclaredFields();
        Map<String, Field> fieldMap = Arrays.stream(fields).collect(Collectors.toMap(k -> k.getName(), v -> v));

        StringBuilder sb = new StringBuilder("[");
        while (rs.next()) {
            if (rs.isFirst()) {
                sb.append("{");
            } else {
                sb.append(",{");
            }
            for (String columnName : columns) {
                Field field = fieldMap.get(reportUtils.camelName(columnName));
                if (field == null) {
                    sb.append(reportUtils.camelName(columnName)).append(":").append("").append(",");
                } else {
                    Class<?> fieldClass = Class.forName(field.getGenericType().getTypeName());
                    Object value = getResultFromResultSet(rs, field.getName(),fieldClass);
                    if (fieldClass == java.util.Date.class) {
                        value = ((Date) value).getTime();
                    }
                    sb.append(field.getName()).append(":").append(value).append(",");
                }
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append("}");
        }
        sb.append("]");
        return sb.toString();
    }

    private <T> String resultSetHandler(ResultSet rs) throws SQLException {
        List<String> columns = getResultRestColumnList(rs);
        StringBuilder sb = new StringBuilder("[");
        while (rs.next()) {
            if (rs.isFirst()) {
                sb.append("{");
            } else {
                sb.append(",{");
            }
            for (String columnName : columns) {
                String value = rs.getString(columnName);
                sb.append(reportUtils.camelOrUnderscoreName(columnName)).append(":").append(value).append(",");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append("}");
        }
        sb.append("]");
        return sb.toString();
    }

    private <T> void resultSetReportHandler(ResultSet rs, OutputStream os, Class<T> classType) throws SQLException, UnsupportedEncodingException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(os, "UTF-8"), true);
        printWriter.flush();

        List<String> columns = getResultRestColumnList(rs);
        Field[] fields = classType.getDeclaredFields();
        Map<String, Field> fieldMap = Arrays.stream(fields).collect(Collectors.toMap(k -> k.getName(), v -> v));

        printWriter.write("[");
        while (rs.next()) {
            StringBuilder sb = new StringBuilder();
            if (rs.isFirst()) {
                sb.append("{");
            } else {
                sb.append(",{");
            }

            for (String columnName : columns) {
                Field field = fieldMap.get(reportUtils.camelName(columnName));
                if (field == null) {
                    sb.append(reportUtils.camelName(columnName)).append(":").append("").append(",");
                } else {
                    Class<?> fieldClass = Class.forName(field.getGenericType().getTypeName());
                    Object value = getResultFromResultSet(rs, field.getName(),fieldClass);
                    if (fieldClass == java.util.Date.class) {
                        value = ((Date) value).getTime();
                    }
                    sb.append(field.getName()).append(":").append(value).append(",");
                }
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append("}");
            printWriter.write(sb.toString());
        }
        printWriter.write("]");
        printWriter.flush();
    }

    private void resultSetReportHandler(ResultSet rs, OutputStream os) throws SQLException, UnsupportedEncodingException {
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(os, "GB18030"), true);
        printWriter.flush();

        List<String> columns = getResultRestColumnList(rs);

        printWriter.write("[");
        while (rs.next()) {
            StringBuilder sb = new StringBuilder();
            if (rs.isFirst()) {
                sb.append("{");
            } else {
                sb.append(",{");
            }
            for (String columnName : columns) {
                String value = rs.getString(columnName);
                sb.append(reportUtils.camelOrUnderscoreName(columnName)).append(":").append(value).append(",");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append("}");
            printWriter.write(sb.toString());
        }
        printWriter.write("]");
        printWriter.flush();
    }

    private <T> void resultSetExportHandler(ResultSet rs, ReportInfo reportInfo, OutputStream os, Class<T> classType) throws SQLException, UnsupportedEncodingException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
//        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(os, "GB18030"), true);
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(os, "UTF-8"), true);
        printWriter.flush();

        List<String> headerList = reportInfo.getReportHeader();
        if (!CollectionUtils.isEmpty(headerList)) {
            String header = String.join(COMMA_SPLIT, headerList);
            printWriter.write(header);
            printWriter.write(LINE_SPLIT);
        }

        List<String> columns = getResultRestColumnList(rs);
        Field[] fields = classType.getDeclaredFields();
        Map<String, Field> fieldMap = Arrays.stream(fields).collect(Collectors.toMap(k -> k.getName(), v -> v));

        List<String> tempLineList = null;
        while (rs.next()) {
            tempLineList = new ArrayList<String>();
            for (String columnName : columns) {
                Field field = fieldMap.get(reportUtils.camelName(columnName));
                if (field == null) {
                    tempLineList.add(formatCsvString(null));
                } else {
                    Class<?> fieldClass = Class.forName(field.getGenericType().getTypeName());
                    Object value = getResultFromResultSet(rs, field.getName(),fieldClass);
                    if (fieldClass == java.util.Date.class) {
                        value = reportUtils.format((Date) value);
                    }
                    tempLineList.add(formatCsvString(value));
                }
            }
            printWriter.write(String.join(COMMA_SPLIT, tempLineList));
            printWriter.write(LINE_SPLIT);
        }
        printWriter.flush();
    }

    private List<String> getResultRestColumnList(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        List<String> columns = new ArrayList<String>();
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            columns.add(columnName);
        }
        return columns;
    }

    private <T> Object getResultFromResultSet(ResultSet resultSet, String name, Class<T> classType) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String columnName = getActColumnNameFromResultSet(resultSet, name);
        if (classType.isEnum()) {
            Method method = classType.getMethod("valueOf", String.class);
            Object enumInstance = method.invoke(null, resultSet.getString(columnName));
            try {
                Method getLabelMethod = classType.getMethod("getLabel");
                return getLabelMethod.invoke(enumInstance);
            } catch (NoSuchMethodException e) {
                return resultSet.getString(columnName);
            }
        } else if (classType == java.util.Date.class || classType == java.sql.Timestamp.class) {
            try {
                return resultSet.getTimestamp(columnName);
            } catch (SQLException e) {
                return resultSet.getString(columnName);
            }
        } else {
            try {
                return resultSet.getObject(columnName, classType);
            } catch (SQLException e) {
                return resultSet.getString(columnName);
            }
        }
    }

    private String getActColumnNameFromResultSet(ResultSet resultSet, String name) throws SQLException {
        try {
            String columnName = reportUtils.camelOrUnderscoreName(name);
            if (resultSet.findColumn(columnName) >= 0) {
                return columnName;
            }
            throw new SQLException("未找到列[" + name + "]");
        } catch (SQLException e) {
            if (resultSet.findColumn(name) >= 0) {
                return name;
            }
            throw e;
        }
    }

    private String formatCsvString(Object result) {
        if (StringUtils.isEmpty(result)) {
            return "=\"\"";
        } else {
            return "=\"" + result.toString() + "\"";
        }
    }
}
