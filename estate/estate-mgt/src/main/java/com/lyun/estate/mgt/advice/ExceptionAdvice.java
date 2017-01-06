package com.lyun.estate.mgt.advice;

import com.lyun.estate.mgt.supports.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler
    public ResponseEntity<?> handleException(Throwable t) {
        return new ResponseEntity<>(new RestResponse().add("message", t.getMessage()).get(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
