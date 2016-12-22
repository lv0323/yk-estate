package com.lyun.estate.core.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyun.estate.core.exception.ErrorResource;
import com.lyun.estate.core.exception.EstateException;
import com.lyun.estate.core.exception.ExCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@ControllerAdvice
public class ExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    private EstateException writeExceptionLog(Throwable t) {
        EstateException estateException = translate(t);
        logger.error("Throwable, id: " + estateException.getId(), t);
        return estateException;
    }

    @ExceptionHandler
    public void processRuntimeException(Throwable t, HttpServletResponse response) {
        EstateException baseException = writeExceptionLog(t);

        ErrorResource errorResource = new ErrorResource(baseException.getId(),
                baseException.getExCode().name(),
                baseException.getLocalizedMessage());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try (OutputStream out = response.getOutputStream()) {
            objectMapper.writeValue(out, errorResource);
        } catch (IOException e) {
            logger.error("write error resource error", e);
        }
    }

    private EstateException translate(Throwable t) {
        if (t instanceof EstateException) {
            return (EstateException) t;
        }
        return new EstateException(t, ExCode.DEFAULT_EXCEPTION);
    }
}
