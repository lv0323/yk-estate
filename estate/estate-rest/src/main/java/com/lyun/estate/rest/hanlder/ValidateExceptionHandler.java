package com.lyun.estate.rest.hanlder;

import com.lyun.estate.core.supports.ErrorMessage;
import com.lyun.estate.core.supports.ExecutionContext;
import com.lyun.estate.core.supports.exceptions.ValidateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ValidateExceptionHandler {
    @Autowired
    MessageSource messageSource;
    @Autowired
    ExecutionContext executionContext;

    @ExceptionHandler({ValidateException.class})
    @ResponseBody
    ResponseEntity<Object> validateWarnExceptionHandler(ValidateException ex, WebRequest webRequest) {
        Map<String, Object> responseBody = new HashMap<>();
        String code = ex.getCode();
        responseBody.put("code", code);
        if (!StringUtils.isEmpty(ex.getObjectErrors())) {
            responseBody.put("message", ex.getObjectErrors().stream().map(objectError -> {
                String errorCode = code + objectError.getCodes()[0];
                return new ErrorMessage()
                        .setCode(errorCode)
                        .setMessage(getMessage(errorCode, objectError.getArguments(), objectError.getDefaultMessage()));
            }).collect(Collectors.toList()));
        } else {
            responseBody.put("message", getMessage(code, null, ex.getMessage()));
        }
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    private String getMessage(String code, Object[] args, String defaultMessage) {
        return messageSource.getMessage(code, args, defaultMessage, new Locale(executionContext.getRequestLocal()));
    }
}
