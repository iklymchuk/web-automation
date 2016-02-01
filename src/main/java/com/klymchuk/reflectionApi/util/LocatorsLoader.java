package com.klymchuk.reflectionApi.util;

import com.klymchuk.reflectionApi.staticData.LocatorType;
import org.openqa.selenium.By;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by iklymchuk on 01.02.16.
 */
public class LocatorsLoader {

    private static Properties LOCATORS_PROPERTIES;

    private LocatorsLoader() {
    }

    static {
        LOCATORS_PROPERTIES = new Properties();
        URL locatorsUrl = ClassLoader.getSystemResource("locators.properties");

        try {
            LOCATORS_PROPERTIES.load(locatorsUrl.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static By get(String locatorKey) {
        return getLocator(LOCATORS_PROPERTIES.getProperty(locatorKey));
    }

    private static By getLocator(String locatorString) {
        String[] locatorItems = locatorString.split("=", 2); // 2 is a limit
        LocatorType locatorType = LocatorType.valueOf(locatorItems[0]);

        switch (locatorType) {
            case ID:
                return By.id(locatorItems[1]);
            case NAME:
                return By.name(locatorItems[1]);
            case CSS:
                return By.cssSelector(locatorItems[1]);
            case XPATH:
                return By.xpath(locatorItems[1]);
            case LINK_TEXT:
                return By.linkText(locatorItems[1]);
            case PARTIAL_LINK_TEXT:
                return By.partialLinkText(locatorItems[1]);
            default:
                throw new IllegalArgumentException("Locator type is unknown: " + locatorType);
        }
    }
}
