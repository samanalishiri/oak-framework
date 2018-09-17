package com.saman.oak.core.database;

import com.saman.oak.core.protocol.Valuable;

/**
 * @author Saman Alishiri
 */
public enum DatabaseKey implements Valuable<String> {
    DRIVER("jdbc.driver"),
    USERNAME("username"),
    PASSWORD("password"),
    URL("url"),
    MAX_IDL("maxIdl"),
    MIN_IDL("minIdl"),
    ;
    private final String value;

    DatabaseKey(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }
}
