package com.saman.oak.core.validation;

/**
 * @author Saman Alishiri
 * @mail samanalishiri@gmail.com
 * @since yyyy-MM-dd
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
