package com.lyun.estate.op.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyun.estate.op.corp.entity.ActionResultBean;
import com.lyun.estate.op.corp.entity.BizIllegalArgumentException;
import com.lyun.estate.op.corp.entity.BizRuntimeException;
import com.lyun.estate.op.corp.entity.NoDataFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by localuser on 2017/5/17.
 */
@ControllerAdvice
public class ExceptionAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    @ExceptionHandler
    public void processRuntimeException(Throwable t, HttpServletResponse response) {

        System.out.println("#####"+t.getMessage());
        if(t instanceof NoDataFoundException){

            try {
                response.getOutputStream().write("{}".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if(t instanceof BizIllegalArgumentException || t instanceof BizRuntimeException){

            ActionResultBean bean = new ActionResultBean().setSuccess(false).setReason(t.getMessage());
            writeResponse(response, bean);
        }else{
            ActionResultBean bean = new ActionResultBean().setSuccess(false).setReason(t.getMessage());
            writeResponse(response, bean);
        }

    }

    private void writeResponse(HttpServletResponse response, Object result) {
        try (OutputStream out = response.getOutputStream()) {
            objectMapper.writeValue(out, result);
        } catch (IOException e) {
            logger.error("write error resource error", e);
        }
    }
}
