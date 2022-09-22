package com.solvd.university.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    public static String givePropertyValue(String key, String propertyPath) {
        Properties dbProperties = new Properties();
        try (InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(propertyPath)) {
            dbProperties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dbProperties.getProperty(key);
    }
}

