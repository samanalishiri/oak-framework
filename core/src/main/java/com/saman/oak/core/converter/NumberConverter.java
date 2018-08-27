package com.saman.oak.core.converter;

public class NumberConverter {

    public static Long convertToLong(Object o) {
        return (Long.parseLong(String.valueOf(o)));
    }
}
