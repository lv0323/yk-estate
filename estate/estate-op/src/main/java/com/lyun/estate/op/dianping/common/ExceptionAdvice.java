package com.lyun.estate.op.dianping.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyun.estate.op.dianping.corp.domain.ActionResultDTO;
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

        } else if(t instanceof BizRuntimeException){

            ActionResultDTO bean = new ActionResultDTO().setSuccess(false).setReason(t.getMessage());
            writeResponse(response, bean);
        }
        else{
            ActionResultDTO bean = new ActionResultDTO().setSuccess(false).setReason(t.getMessage());
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
