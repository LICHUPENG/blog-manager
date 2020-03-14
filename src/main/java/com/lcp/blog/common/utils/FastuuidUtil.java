package com.lcp.blog.common.utils;

import java.util.Random;

public class FastuuidUtil {

    private static final long being_timestamp = 1206576000; // 2008-03-27

    private static final int suffix_len = 3;

    public static String getTime(){
        long mt = System.currentTimeMillis();
        long now_timestamp = mt/1000L;
        return String.valueOf(now_timestamp - being_timestamp);
    }

    /** 源于QeePHP Fastuuid 缺省生成策略 */
    public static String genUUID() {

        long mt = System.currentTimeMillis();
        long now_timestamp = mt/1000L;
        long nanoTime = System.nanoTime();

        Random random = new Random();
        String nanoTimeStr = String.valueOf(nanoTime);
        nanoTimeStr = nanoTimeStr.substring(nanoTimeStr.length() - 7, nanoTimeStr.length() -1);

        return (now_timestamp - being_timestamp) + nanoTimeStr + String.valueOf(random.nextInt()).substring(3, 3 + suffix_len);

    }

    public static void main(String[] args) {
        System.out.println(genUUID());
    }

}
