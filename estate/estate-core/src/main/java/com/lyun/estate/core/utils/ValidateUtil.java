package com.lyun.estate.core.utils;

import org.springframework.util.StringUtils;

public class ValidateUtil {
    public static boolean lengthMax(String str, int maxLength) {
        if (str == null) {
            return true;
        }
        return str.trim().length() <= maxLength;
    }

    public static boolean lengthMin(String str, int minLength) {
        if (str == null) {
            return true;
        }
        return str.trim().length() >= minLength;
    }

    public static boolean lengthBetween(String str, int minLength, int maxLength) {
        if (str == null) {
            return true;
        }
        return lengthMax(str, maxLength) && lengthMin(str, minLength);
    }


    public static boolean isMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return false;
        }
        return mobile.matches("[0-9]{11}");
    }

    public static boolean isPassword(String password) {
        if (StringUtils.isEmpty(password)) {
            return false;
        }
        return password.matches("[0-9a-zA-Z.<>?!@#$^&*_=+]{8,32}");
    }
}
