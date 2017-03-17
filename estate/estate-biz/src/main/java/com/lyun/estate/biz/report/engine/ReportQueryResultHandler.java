package com.lyun.estate.biz.report.engine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import javax.annotation.PostConstruct;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by jesse on 2017/3/13.
 */
public class ReportQueryResultHandler implements ResultHandler {
    private PrintWriter printWriter;
    private ObjectMapper mapper = new ObjectMapper();
    private List resultList;
    private boolean isStream = false;

    ReportQueryResultHandler(PrintWriter printWriter) {
        assert this.printWriter != null;
        this.printWriter = printWriter;
        this.isStream = true;
    }

    ReportQueryResultHandler(List resultList) {
        assert this.resultList != null;
        this.resultList = resultList;
    }

    @PostConstruct
    public void  init() {
        this.mapper.setDateFormat(new ISO8601DateFormat());
    }

    @Override
    public void handleResult(ResultContext resultContext) {
        if (isStream) {
            streamProcess(resultContext);
        } else {
            batchProcess(resultContext);
        }
    }

    private void batchProcess(ResultContext resultContext) {
        resultList.add(resultContext.getResultObject());
    }

    private void streamProcess(ResultContext resultContext) {
        try {
            String jsonString = mapper.writeValueAsString(resultContext.getResultObject());
            printWriter.write(jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
