package com.aaomidi.soritingtelegram.util;

import java.security.SecureRandom;

/**
 * Created by amir on 2015-11-25.
 */
public class StringManager {
    private static String chars = "3eRIEbmCaXs1ktjiN7AP5gqfvrTGdJ4yMBUuQWDhKoSF8wZ6HxL2YlcpVn9z";
    private static SecureRandom rnd = new SecureRandom();

    public static void log(String s, Object... format) {
        String msg = String.format(s, format);
        System.out.print(msg);
    }

    public static String generateString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }

    public static void logn(String s, Object... format) {
        log(s + "%n", format);
    }
}
