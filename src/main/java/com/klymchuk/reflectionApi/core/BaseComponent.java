package com.klymchuk.reflectionApi.core;

import org.openqa.selenium.WebDriver;

/**
 * Created by iklymchuk on 01.02.16.
 */
public class BaseComponent {

    protected WebDriver driver;

    public BaseComponent(WebDriver driver) {
        this.driver = driver;
    }
}
