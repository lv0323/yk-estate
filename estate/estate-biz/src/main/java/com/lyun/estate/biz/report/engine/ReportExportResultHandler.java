package com.lyun.estate.biz.report.engine;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by jesse on 2017/3/13.
 */
public class ReportExportResultHandler implements ResultHandler {

    private PrintWriter printWriter;
    private List<String> resultList = new ArrayList<>();
    private List<ReportHeader> headerList;
    private boolean isStream;
    private ResultObjectHandler resultObjectHandler;


    ReportExportResultHandler(PrintWriter printWriter, List<ReportHeader> headerList, ResultObjectHandler resultObjectHandler) {
        this.printWriter = printWriter;
        this.headerList = headerList;
        this.resultObjectHandler = resultObjectHandler;
        this.isStream = true;
    }

    ReportExportResultHandler(List<ReportHeader> headerList, ResultObjectHandler resultObjectHandler) {
        this.headerList = headerList;
        this.resultObjectHandler = resultObjectHandler;
        this.isStream = false;
    }

    public List<String> getResultList() {
        return resultList;
    }

    @Override
    public void handleResult(ResultContext resultContext) {
        try {
            Map<String, Object> resultMap = this.resultObjectHandler.handlerResultObject(resultContext, headerList);
            List<String> valueList = resultMap.entrySet().stream()
                    .map(v -> v.getValue() == null ? "" : ReportUtils.stringFilter(v.getValue().toString()))
                    .collect(Collectors.toList());
            String values = valueList.stream().collect(Collectors.joining(ReportEngine.COMMA_SPLIT));
            if (isStream) {
                streamProcess(values);
            } else {
                batchProcess(values);
            }
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            this.resultList = null;
            this.printWriter = null;
        }
    }

    private void batchProcess(String s) {
        resultList.add(s);
    }

    private void streamProcess(String s) {
        printWriter.write(s);
        printWriter.write(ReportEngine.LINE_SPLIT);
        printWriter.flush();
    }
}
