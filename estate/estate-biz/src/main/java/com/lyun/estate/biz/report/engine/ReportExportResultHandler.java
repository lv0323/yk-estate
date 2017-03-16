package com.lyun.estate.biz.report.engine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import javax.annotation.PostConstruct;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

/**
 * Created by jesse on 2017/3/13.
 */
public class ReportExportResultHandler implements ResultHandler {
    private PrintWriter printWriter;
    private ObjectMapper mapper = new ObjectMapper();
    private List resultList;
    private boolean isStream = false;
    private TimeZone tz = TimeZone.getTimeZone("UTC");
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private ISO8601DateFormat ios8601 = new ISO8601DateFormat();


    ReportExportResultHandler(PrintWriter printWriter) {
        assert this.printWriter != null;
        this.printWriter = printWriter;
        this.isStream = true;
    }

    ReportExportResultHandler(List resultList) {
        assert this.resultList != null;
        this.resultList = resultList;
    }

    @PostConstruct
    public void  init() {
        this.mapper.setDateFormat(new ISO8601DateFormat());
        this.df.setTimeZone(tz);
    }

    @Override
    public void handleResult(ResultContext resultContext) {
        Map<String, Object> resultMap = (LinkedHashMap<String, Object>)resultContext.getResultObject();
        List<String> valueList =resultMap.entrySet().stream()
                .map(v -> {
                    Object value = v.getValue();
                    if (value == null) {
                        return "";
                    }
                    if (value.getClass() == Date.class || value.getClass() == Timestamp.class || value.getClass() == java.sql.Date.class) {
                        Date date = (Date) v.getValue();
                        return ios8601.format(date);
                    }
                    return value.toString();
                })
                .collect(Collectors.toList());
        String values = valueList.stream()
                .collect(Collectors.joining(ReportEngine.COMMA_SPLIT));
        if (isStream) {
            streamProcess(values);
        } else {
            batchProcess(values);
        }
    }

    private void batchProcess(String s) {
        resultList.add(s);
    }

    private void streamProcess(String s) {
        printWriter.write(s);
        printWriter.write(ReportEngine.LINE_SPLIT);
    }
}
