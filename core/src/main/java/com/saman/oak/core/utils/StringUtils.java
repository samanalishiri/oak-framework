package com.saman.oak.core.utils;

public class StringUtils {

    public static final String EMPTY = "";
    public static final String SPACE = " ";

    public static String firstToLowerCase(String s) {

        char[] chars = s.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);

        return new String(chars);
    }

    public static String join(String separator, String... args) {
        return org.apache.commons.lang3.StringUtils.join(args, separator);
    }

}
