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
        if (!StringUtils.isEmpty(mobile)) {
            return mobile.matches("[0-9]{11}");
        }
        return false;
    }

    public static boolean isPassword(String password) {
        if (!StringUtils.isEmpty(password)) {
            return password.matches("[0-9a-zA-Z.<>?!@#$%^&*_=\\-+()]{8,32}");
        }
        return false;
    }

    public static boolean isEmail(String email) {
        if (!StringUtils.isEmpty(email)) {
            return email.matches("(.)*@(.)*(\\.)(.)*");
        }
        return false;
    }

}
