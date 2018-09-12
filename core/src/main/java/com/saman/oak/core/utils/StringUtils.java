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

    public static boolean isEmpty(String s) {
        return (s == null || s.isEmpty());
    }

    public static boolean notEmpty(String s) {
        return (s != null && !s.isEmpty());
    }

    public static boolean isEqual(String s1, String s2) {
        return notEmpty(s1) && s1.equals(s2);
    }

    public static boolean notEqual(String s1, String s2) {
        return isEmpty(s1) || !s1.equals(s2);
    }
}
