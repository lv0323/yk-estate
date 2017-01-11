package com.lyun.estate.amqp.exceptions;

import org.springframework.messaging.MessagingException;

public class AmqpBizException extends MessagingException {
    private String code;

    public AmqpBizException(String code) {
        super(code);
        this.code = code;
    }

    public AmqpBizException(String code, String message) {
        super(message);
        this.code = code;
    }

    public AmqpBizException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public AmqpBizException setCode(String code) {
        this.code = code;
        return this;
    }
}
