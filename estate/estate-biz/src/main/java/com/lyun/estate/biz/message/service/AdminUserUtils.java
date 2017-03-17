package com.lyun.estate.biz.message.service;

import com.lyun.estate.biz.message.def.ContentType;
import org.springframework.stereotype.Service;

/**
 * Created by jesse on 2017/3/3.
 */
@Service
public class AdminUserUtils {

    public static Long getSenderIdByContentType(ContentType contentType) {
        switch (contentType) {
            case FANG_SUMMARY:
                return 1L;
            case FANG_PROCESS:
                return 2L;
            case NOTICE:
                return 3L;
            case XIAO_QU_REPORT:
                return 4L;
            default:
                return null;
        }
    }
}
