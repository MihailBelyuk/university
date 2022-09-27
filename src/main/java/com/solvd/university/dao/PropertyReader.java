package com.solvd.university.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static final Logger LOGGER = LogManager.getLogger(PropertyReader.class);

    private static final Properties properties;
    private static final String DB_PROPERTIES = "properties/db.properties";

    static {
        properties = new Properties();
    }

    public static String getValue(String key) {
        try (InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(DB_PROPERTIES)) {
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("Failed to read properties file.");
        }
        return properties.getProperty(key);
    }
}

