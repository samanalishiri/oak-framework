package ir.navaco.core.util;

import java.util.Map;
import java.util.Properties;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public class PropertiesBuilder {

    private Properties properties = new Properties();

    private PropertiesBuilder() {
    }

    public static PropertiesBuilder NEW() {
        return new PropertiesBuilder();
    }

    public PropertiesBuilder put(Object key, Object value) {
        properties.put(key, value);
        return this;
    }

    public PropertiesBuilder put(Map map) {
        properties.putAll(map);
        return this;
    }

    public Properties get() {
        return properties;
    }
}
