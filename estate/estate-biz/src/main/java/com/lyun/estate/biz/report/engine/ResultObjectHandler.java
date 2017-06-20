package com.lyun.estate.biz.report.engine;

import org.apache.ibatis.session.ResultContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class ResultObjectHandler {
    private final static Logger logger = LoggerFactory.getLogger(ResultObjectHandler.class);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @SuppressWarnings("unchecked")
    public Map<String, Object> handlerResultObject(ResultContext resultContext, List<ReportHeader> headerList) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<String, Object> resultMap = (LinkedHashMap<String, Object>) resultContext.getResultObject();
        if (!CollectionUtils.isEmpty(resultMap)) {
            int i = 0;
            Set<Map.Entry<String, Object>> entries = resultMap.entrySet();
            for (Map.Entry<String, Object> e : entries) {
                ReportHeader reportHeader = headerList.get(i);
                if (e.getValue() == null) {
                    e.setValue(null);
                } else {
                    Class typeClass = null;
                    if (reportHeader == null) {
                        typeClass = String.class;
                    } else {
                        typeClass = reportHeader.getType();
                    }
                    assert typeClass != null;
                    if (typeClass.isEnum()) {
                        Method method = typeClass.getMethod("valueOf", String.class);
                        Object enumInstance = method.invoke(null, e.getValue().toString());
                        try {
                            method = typeClass.getMethod("label");
                            String code = (String) method.invoke(enumInstance);
                            e.setValue(code);
                        } catch (NoSuchMethodException ex) {
                            e.setValue(enumInstance);
                        }
                    } else if (typeClass == Date.class) {
                        Date date = (Date) e.getValue();
                        String formattedDate = dateFormat.format(date);
                        e.setValue(formattedDate);
                    } else if (typeClass == Long.class) {
                        e.setValue(Long.valueOf(e.getValue().toString()));
                    } else if (typeClass == Integer.class) {
                        e.setValue(Integer.valueOf(e.getValue().toString()));
                    } else {
                        e.setValue(typeClass.cast(e.getValue()));
                    }
                }
                i++;
            }
        }
        return resultMap;
    }
}
