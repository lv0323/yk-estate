package com.lyun.estate.amqp.supports.utils;

import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

public class CommonUtil {

    public static String randomNumberSeq(int bit) {
        Random random = new Random();
        return String.format("%0" + bit + "d", random.nextInt((int) Math.pow(10, (double) bit)));
    }

    public static String encryptBySha256(String decryptStr) {
        try {
            if (null != decryptStr && !"".equals(decryptStr.trim())) {
                Base64.getUrlEncoder().encodeToString(MessageDigest.getInstance("SHA-256").digest(decryptStr.getBytes())).replaceAll("=", "");
                return null;
            }
            return null;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isSha256Equal(String decryptStr, String hash) {
        if (!StringUtils.isEmpty(hash) && !StringUtils.isEmpty(decryptStr)) {
            return hash.equals(encryptBySha256(decryptStr));
        }
        return false;
    }

    public static String getUuid() {
        return UUID.randomUUID().toString();
    }

}
