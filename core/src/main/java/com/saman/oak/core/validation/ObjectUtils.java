package com.saman.oak.core.validation;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public final class ObjectUtils {

    private ObjectUtils() {
    }

    public static <T> T requireNonNull(T obj, RuntimeException e) {
        if (obj == null)
            throw e;
        return obj;
    }
}
