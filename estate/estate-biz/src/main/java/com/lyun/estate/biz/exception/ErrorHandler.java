package com.lyun.estate.biz.exception;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMsg> processRuntimeException(Exception ex) {
        ResponseEntity.BodyBuilder builder;
        ErrorMsg errorMsg;
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        if (responseStatus != null) {
            builder = ResponseEntity.status(responseStatus.value());
            errorMsg = new ErrorMsg(responseStatus.value().name(), responseStatus.reason());
        } else {
            builder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            errorMsg = new ErrorMsg(HttpStatus.INTERNAL_SERVER_ERROR.name(), "Internal server error");
        }
        return builder.body(errorMsg);
    }

}
