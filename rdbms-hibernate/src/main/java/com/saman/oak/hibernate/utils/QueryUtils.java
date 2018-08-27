package com.saman.oak.hibernate.utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Saman Alishiri
 */
public class QueryUtils {

    public static String loadQuery(String name) {
        InputStream sqlFile = null;
        String query = new String();

        try {
            sqlFile = Thread.currentThread().getContextClassLoader().getResourceAsStream(name + ".sql");
            query = IOUtils.toString(sqlFile, "UTF-8");

        } catch (IOException e) {
            throw new IllegalStateException("Failed to read SQL file", e);
        } finally {
            IOUtils.closeQuietly(sqlFile);
        }
        return query;
    }
}
