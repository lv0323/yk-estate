package com.lyun.estate.biz.report.engine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReportQueryResultHandler implements ResultHandler {

    private ObjectMapper mapper = new ObjectMapper();
    private PrintWriter printWriter;
    private List<Map<String, Object>> resultList = new ArrayList<>();
    private List<ReportHeader> headerList;
    private boolean isStream;
    private ResultObjectHandler resultObjectHandler;


    ReportQueryResultHandler(PrintWriter printWriter, List<ReportHeader> headerList, ResultObjectHandler resultObjectHandler) {
        this.printWriter = printWriter;
        this.headerList = headerList;
        this.resultObjectHandler = resultObjectHandler;
        this.isStream = true;
    }

    ReportQueryResultHandler(List<ReportHeader> headerList, ResultObjectHandler resultObjectHandler) {
        this.headerList = headerList;
        this.resultObjectHandler = resultObjectHandler;
        this.isStream = false;
    }

    public List<Map<String, Object>> getResultList() {
        return resultList;
    }

    @Override
    public void handleResult(ResultContext resultContext) {
        // this link to report mapper 's result type
        try {
            Map<String, Object> resultMap = this.resultObjectHandler.handlerResultObject(resultContext, headerList);
            if (isStream) {
                streamProcess(resultMap);
            } else {
                batchProcess(resultMap);
            }
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            this.resultList = null;
            this.printWriter = null;
        }
    }

    private void batchProcess(Map<String, Object> resultMap) {
        resultList.add(resultMap);
    }

    private void streamProcess(Map<String, Object> resultMap) {
        try {
            String jsonString = mapper.writeValueAsString(resultMap);
            printWriter.write(jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
