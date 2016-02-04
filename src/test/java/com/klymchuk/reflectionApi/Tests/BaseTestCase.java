package com.klymchuk.reflectionApi.Tests;

import com.klymchuk.reflectionApi.util.PropertyLoader;
import com.klymchuk.reflectionApi.util.ScreenshotListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.util.concurrent.TimeUnit;

/**
 * Created by iklymchuk on 04.02.16.
 * abstract base class
 */
@Listeners({ScreenshotListener.class})
public abstract class BaseTestCase {

    private static WebDriver driver;

    @BeforeSuite
    public void SetUpBeforeSuite() {}

    @BeforeClass
    public void setUpBeforeClass() {}

    @AfterSuite
    public void tearDownAfterSuite() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected  WebDriver getDriver() {
        if (driver == null) {
            String browser = PropertyLoader.getTestProperty("browser");
            if ("firefox".equals(browser)) {
                driver = new FirefoxDriver();
            } else if ("chrome".equals(browser)) {
                driver = new ChromeDriver();
            } else if ("htmlunit".equals(browser)) {
                driver = new HtmlUnitDriver(true);
                return driver;
            }
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(PropertyLoader.getTestProperty("wait.time.sec")), TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(PropertyLoader.getTestProperty("wait.page.load")), TimeUnit.SECONDS);
        }
        return driver;
    }

    public void openUrl(String currentUrl) {
        getDriver().get(currentUrl);
    }

}
