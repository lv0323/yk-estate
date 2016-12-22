package com.lyun.estate.core.utils;

import com.lyun.estate.core.exception.ExceptionUtil;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.net.URLDecoder;
import java.util.Arrays;

/**
 * Created by maru on 11/26/15.
 */
public class QueryStringUtil {

    public static MultiValueMap<String, String> parse(String queryString) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        if (StringUtils.hasText(queryString)) {
            Arrays.stream(queryString.split("&")).forEach(p -> {
                int equalIdx = p.indexOf('=');
                if (equalIdx > 0) {
                    String key = decode(p.substring(0, equalIdx).trim());
                    String value = "";
                    if (equalIdx + 1 < p.length()) {
                        value = decode(p.substring(equalIdx + 1).trim());
                    }

                    if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value)) {
                        map.add(key, value);
                    }
                }
            });
        }

        return map;
    }

    private static String decode(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (Exception ex) {
            ExceptionUtil.catching(ex);
        }
        return str;
    }
}
