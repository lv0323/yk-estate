package com.lyun.estate.core.utils;

import java.util.Random;

public class CommonUtil {

    public static String randomNumberSeq(int bit) {
        Random random = new Random();
        return String.format("%0" + bit + "d", random.nextInt((int) Math.pow(10, (double) bit)));
    }

}
