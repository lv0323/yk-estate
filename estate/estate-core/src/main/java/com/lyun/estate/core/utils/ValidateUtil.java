package com.lyun.estate.core.utils;

import com.google.common.collect.Sets;
import com.lyun.estate.core.supports.types.Constant;
import org.springframework.util.StringUtils;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {
    private static final Set<String> PROVINCE_CODE = Sets.newHashSet("11", "12", "13", "14", "15", "21", "22", "23",
            "31", "32", "33", "34", "35", "36", "37", "41", "42", "43", "44", "45", "46",
            "50", "51", "52", "53", "54", "61", "62", "63", "64", "65", "71", "81", "82");
    private static final Pattern ID_NUMBER_PATTERN = Pattern.compile(
            "^(\\d{2})\\d{4}(19|20)\\d{2}(\\d{2})(\\d{2})\\d{3}[0-9X]{1}$");
    private static int[] ID_CONST = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    private static char[] RE_CONST = new char[]{'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

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
            return mobile.matches("1[0-9]{10}");
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

    public static boolean isClientId(int clientId) {
        return Constant.CLIENT_ID.getClients().contains(clientId);
    }

    /**
     * 判断是否为合法的身份证号码.
     */
    public static boolean isIdNo(String idNumber) {
        Matcher matcher = ID_NUMBER_PATTERN.matcher(idNumber);

        if (!matcher.matches()) {
            return false;
        }

        if (!PROVINCE_CODE.contains(matcher.group(1))) {
            return false;
        }

        if (Integer.parseInt(matcher.group(3)) < 1 || Integer.parseInt(matcher.group(3)) > 12) {
            return false;
        }

        if (Integer.parseInt(matcher.group(4)) < 1 || Integer.parseInt(matcher.group(4)) > 31) {
            return false;
        }

        // 判断第18位校验码是否正确
        int i = 0, y = 0;
        for (char ch : idNumber.toCharArray()) {
            if (i < 17) {
                y += ID_CONST[i] * Integer.parseInt(ch + "");
            } else {
                return RE_CONST[y % 11] == ch;
            }
            i++;
        }

        return false;
    }

}
