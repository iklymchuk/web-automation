package com.klymchuk.reflectionApi.util;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by iklymchuk on 01.02.16.
 */
public class PropertyLoader {

    private static Properties TEST_PROPERTIES;

    private PropertyLoader() {
    }

    static {
        TEST_PROPERTIES = new Properties();
        URL testUrl = ClassLoader.getSystemResource("configuration.properties");

        try {
            TEST_PROPERTIES.load(testUrl.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getTestProperty(String key) {
        return TEST_PROPERTIES.getProperty(key);
    }
}
