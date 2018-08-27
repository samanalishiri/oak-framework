package com.saman.oak.core.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericUtils {

    public static Type extract(Class<?> c, int index) {
        Type[] generics = ParameterizedType.class.cast(c.getGenericSuperclass()).getActualTypeArguments();
        return generics[index];
    }

    public static <T> T extract(Class<?> c, int index, Class<T> type) {
        return (T) extract(c, index);
    }

}
