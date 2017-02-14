package com.lyun.estate.mgt.advice;

import com.lyun.estate.core.supports.exceptions.ErrorResource;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.mgt.context.MgtContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);
    @Autowired
    private MgtContext mgtContext;

    @ExceptionHandler
    public ResponseEntity<?> handleException(Throwable t) {
        if (!(t instanceof EstateException)) {
            t = new EstateException(t, ExCode.DEFAULT_EXCEPTION);
        }
        EstateException exception = (EstateException) t;
        logger.error("Throwable, id: " + mgtContext.getCorrelationId(), exception);
        return new ResponseEntity<>(new ErrorResource(mgtContext.getCorrelationId(),
                exception.getExCode().name(),
                exception.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
