package com.klymchuk.reflectionApi.core;

import com.klymchuk.reflectionApi.annotations.Component;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * Created by iklymchuk on 01.02.16.
 * reflection API
 */
public class CustomPageFactory {

    public static <T> T init(WebDriver driver, Class<T> pageClassToProxy) {
        return initElements(new DefaultElementLocatorFactory(driver), driver, pageClassToProxy);
    }


    public static <T extends BasePage> void initWithFactory(ElementLocatorFactory factory, WebDriver driver, T page) {
        PageFactory.initElements(factory, page);
        initComponents(factory, driver, page);
    }

    private static <T> T initElements(ElementLocatorFactory factory, WebDriver driver, Class<T> pageClassToProxy) {
        T component = instantiatePage(driver, pageClassToProxy);
        PageFactory.initElements(factory, component);
        initComponents(factory, driver, component);
        return component;
    }

    private static <T> void initComponents(ElementLocatorFactory factory, WebDriver driver, T component) {
        Class<?> proxyIn = component.getClass();
        while (proxyIn != Object.class) {
            proxyFields(factory, driver, component, proxyIn);
            proxyIn = proxyIn.getSuperclass();
        }
    }

    private static void proxyFields(ElementLocatorFactory factory, WebDriver driver, Object page, Class<?> proxyIn) {
        Field[] fields = proxyIn.getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(Component.class) != null) {
                try {
                    field.setAccessible(true);
                    field.set(page, initElements(factory, driver, field.getType()));
                } catch (Exception e) {
                    throw  new RuntimeException(e);
                }
            }
        }
    }

    private static <T> T instantiatePage(WebDriver driver, Class<T> pageClassToProxy) {
        try {
            try {
                Constructor<T> constructor = pageClassToProxy.getConstructor(WebDriver.class);
                return constructor.newInstance(driver);
            } catch (NoSuchMethodException e) {
                return pageClassToProxy.newInstance();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
