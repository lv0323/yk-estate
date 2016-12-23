package com.lyun.estate.core.supports.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;

public class ExceptionUtil {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionUtil.class);

    static String format(ExCode code, Object... args) {
        if (code == null || StringUtils.isEmpty(code.getMessageTemplate())) {
            return "";
        }
        if (args == null || args.length == 0) {
            return code.getMessageTemplate();
        }

        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (String part : code.getMessageTemplate().split("\\{ *\\}", -1)) {
            if (index != 0) {
                sb.append("{");
                sb.append(index - 1);
                sb.append("}");
            }
            sb.append(part);
            ++index;
        }
        return MessageFormat.format(sb.toString(), args);
    }


    public static void catching(Throwable throwable) {
        logger.warn(MessageFormat.format("Catching exception {0} : {1}",
                throwable.getClass(),
                throwable.getLocalizedMessage()), throwable);
    }

    public static void checkIllegal(boolean express, String param, Object value) {
        if (!express) {
            throw new EstateException(ExCode.PARAM_ILLEGAL, param, value);
        }
    }


}
