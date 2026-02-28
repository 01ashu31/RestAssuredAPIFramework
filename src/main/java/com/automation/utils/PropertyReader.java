package com.automation.utils;

import java.io.InputStream;
import java.util.Properties;

public final class PropertyReader {
    private static final String CONFIG_FILE = "config.properties";
    private static final Properties PROPERTIES = load();

    private PropertyReader() {
    }

    private static Properties load() {
        try (InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (stream == null) {
                throw new IllegalStateException("Unable to load " + CONFIG_FILE + " from classpath.");
            }
            Properties properties = new Properties();
            properties.load(stream);
            return properties;
        } catch (Exception e) {
            throw new IllegalStateException("Unable to read configuration file: " + CONFIG_FILE, e);
        }
    }

    public static String get(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Missing configuration value for key: " + key);
        }
        return value.trim();
    }
}
