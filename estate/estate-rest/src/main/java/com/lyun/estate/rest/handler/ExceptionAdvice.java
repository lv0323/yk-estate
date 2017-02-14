package com.lyun.estate.rest.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyun.estate.core.supports.context.RestContext;
import com.lyun.estate.core.supports.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @Autowired
    RestContext restContext;

    private ObjectMapper objectMapper = new ObjectMapper();


    private EstateException writeExceptionLog(Throwable t) {
        EstateException estateException = translate(t);
        logger.error("Throwable, id: " + restContext.getCorrelationId(), t);
        return estateException;
    }

    @ExceptionHandler
    public void processRuntimeException(Throwable t, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        if (t instanceof ValidateException) {
            logger.warn("校验失败", t);
            writeResponse(response, handleValidateExceptionResult((ValidateException) t));
        } else if (t instanceof ServletRequestBindingException) {
            logger.warn("Spring参数校验失败", t);
            writeResponse(response, handleRequireParamMissException((ServletRequestBindingException) t));
        } else if (t instanceof BindException || t instanceof IllegalArgumentException) {
            logger.warn("请求参数不正确", t);
            writeResponse(response, new ErrorResource()
                    .setExCode("param.illegal")
                    .setMessage("参数异常")
                    .setLogRef(restContext.getCorrelationId()));
        } else if (t instanceof EasyCodeException) {
            logger.error("业务异常", t);
            writeResponse(response, handleEasyCodeException((EasyCodeException) t));
        } else {
            EstateException baseException = writeExceptionLog(t);
            ErrorResource errorResource = new ErrorResource(restContext.getCorrelationId(),
                    baseException.getExCode().name(),
                    baseException.getLocalizedMessage());
            writeResponse(response, errorResource);
        }
    }


    private EstateException translate(Throwable t) {
        if (t instanceof EstateException) {
            return (EstateException) t;
        }
        return new EstateException(t, ExCode.DEFAULT_EXCEPTION);
    }

    private Map<String, Object> handleValidateExceptionResult(ValidateException ex) {
        Map<String, Object> responseBody = new HashMap<>();
        String code = ex.getCode();
        responseBody.put("logRef", restContext.getCorrelationId());
        responseBody.put("exCode", code);
        responseBody.put("message", getMessage(code, null, ex.getMessage()));
        if (!StringUtils.isEmpty(ex.getObjectErrors())) {
            if (ex.getObjectErrors().size() > 1) {
                responseBody.put("messages", ex.getObjectErrors().stream().map(objectError -> {
                    String errorCode = objectError.getCodes()[0];
                    return new ErrorResource()
                            .setExCode(errorCode)
                            .setMessage(getMessage(errorCode,
                                    objectError.getArguments(),
                                    objectError.getDefaultMessage()));
                }).collect(Collectors.toList()));
            } else {
                ObjectError objectError = ex.getObjectErrors().get(0);
                responseBody.put("message",
                        getMessage(objectError.getCode(), objectError.getArguments(), objectError.getDefaultMessage()));
            }
        }
        return responseBody;
    }

    private ErrorResource handleRequireParamMissException(ServletRequestBindingException t) {
        String errorMessage = t.getMessage();
        if (!StringUtils.isEmpty(errorMessage) &&
                (errorMessage.contains("Missing request") || errorMessage.contains("Required"))) {
            Pattern p = Pattern.compile("'([^']*?)'");
            Matcher matcher = p.matcher(errorMessage);
            String parameterName = "";
            if (matcher.find()) {
                parameterName = matcher.group().replaceAll("'", "");
            }
            return new ErrorResource()
                    .setExCode("request.parameter.miss")
                    .setLogRef(restContext.getCorrelationId())
                    .setMessage(getMessage("request.parameter.miss", new String[]{parameterName}, "参数{0}缺失"));
        }
        throw new RuntimeException(t);
    }

    private ErrorResource handleEasyCodeException(EasyCodeException t) {
        return new ErrorResource()
                .setExCode(t.getCode())
                .setLogRef(restContext.getCorrelationId())
                .setMessage(getMessage(t.getCode(), null, t.getMessage()));
    }

    private void writeResponse(HttpServletResponse response, Object result) {
        try (OutputStream out = response.getOutputStream()) {
            objectMapper.writeValue(out, result);
        } catch (IOException e) {
            logger.error("write error resource error", e);
        }
    }

    private String getMessage(String code, Object[] args, String defaultMessage) {
        return defaultMessage;
    }

}
