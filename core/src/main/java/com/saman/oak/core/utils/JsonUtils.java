package com.saman.oak.core.utils;

import com.google.gson.Gson;

/**
 * @author Saman Alishiri
 */
public class JsonUtils {

    public static <T> T createModelFromString(String str, Class<T> model) {
        Gson gson = new Gson();
        return gson.fromJson(str, model);
    }


}
