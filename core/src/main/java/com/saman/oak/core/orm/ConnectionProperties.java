package com.saman.oak.core.orm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum ConnectionProperties {

    APACHE_DHCP(new HashMap() {{
        put("hibernate.connection.initial", "hibernate.dbcp.initialSize");
        put("hibernate.connection.active", "hibernate.dbcp.maxActive");
        put("hibernate.connection.max", "hibernate.dbcp.maxIdle");
        put("hibernate.connection.min", "hibernate.dbcp.minIdle");
        put("hibernate.connection.timeout", "");
        put("hibernate.connection.max_statements", "");
    }}),
    APACHE_C3P0(new HashMap() {{
        put("hibernate.connection.initial", "");
        put("hibernate.connection.active", "");
        put("hibernate.connection.max", "hibernate.c3p0.max_size");
        put("hibernate.connection.min", "hibernate.c3p0.min_size");
        put("hibernate.connection.timeout", "hibernate.c3p0.timeout");
        put("hibernate.connection.max_statements", "hibernate.c3p0.max_statements");
    }}),
    ;

    private final Map<String, String> map;

    ConnectionProperties(Map<String, String> map) {
        this.map = map;
    }

    public Set<String> keys() {
        return map.keySet();
    }

    public String value(String key) {
        return map.get(key);
    }

    public static ConnectionProperties getConnectionProperties(String name) {
        return ConnectionProperties.valueOf(name.toUpperCase());
    }
}
