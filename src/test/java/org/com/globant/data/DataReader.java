package org.com.globant.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Loads test data from the classpath properties file (data layer).
 */
public final class DataReader {

    private static final String DATA_FILE = "data/testdata.properties";
    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream inputStream = DataReader.class.getClassLoader().getResourceAsStream(DATA_FILE)) {
            if (inputStream == null) {
                throw new IllegalStateException("Test data file not found on classpath: " + DATA_FILE);
            }
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Unable to load test data from " + DATA_FILE + ": " + e.getMessage());
        }
    }

    private DataReader() {
    }

    public static String get(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Missing test data key: " + key);
        }
        return value.trim();
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }
}