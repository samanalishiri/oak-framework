package com.saman.oak.core.properties;

import java.util.Map;
import java.util.Properties;

public class PropertiesHelper {

    private Properties properties = new Properties();

    private PropertiesHelper() {
    }

    public static PropertiesHelper NEW() {
        return new PropertiesHelper();
    }

    public PropertiesHelper put(Object key, Object value) {
        properties.put(key, value);
        return this;
    }

    public PropertiesHelper put(Map map) {
        properties.putAll(map);
        return this;
    }

    public Properties get() {
        return properties;
    }
}
