package com.saman.oak.core.properties;

import com.saman.oak.core.utils.StringUtils;
import org.springframework.core.env.Environment;

/**
 * Created by saman on 9/21/2017.
 */
public class EnvironmentHelper {
    private final Environment env;

    public EnvironmentHelper(Environment env) {
        this.env = env;
    }

    public Environment get() {
        return env;
    }

    public boolean has(String key) {
        return env.containsProperty(key);
    }

    public String value(String key) {
        return has(key) ? env.getRequiredProperty(key) : StringUtils.EMPTY;
    }

    public String uppercase(String key) {
        return has(key) ? env.getRequiredProperty(key).toUpperCase() : StringUtils.EMPTY;
    }

    public String value(String key, String def) {
        return has(key) ? env.getRequiredProperty(key) : def;
    }

    public int intValue(String key) {
        return has(key) ? env.getRequiredProperty(key, Integer.class) : 0;
    }

    public int intValue(String key, int def) {
        return has(key) ? env.getRequiredProperty(key, Integer.class) : def;
    }

}
