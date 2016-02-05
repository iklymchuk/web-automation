package com.klymchuk.reflectionApi.Tests;

import static org.testng.AssertJUnit.assertTrue;

import com.klymchuk.reflectionApi.core.BasePage;
import com.klymchuk.reflectionApi.pages.HomePage;
import com.klymchuk.reflectionApi.pages.LoginPage;
import com.klymchuk.reflectionApi.util.PropertyLoader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

/**
 * Created by iklymchuk on 04.02.16.
 */
@Features("Login Feature")
public class LoginPositiveTest extends BaseTestCase {

    private LoginPage loginPage;
    private HomePage homePage;
    private WebDriver dr = getDriver();

    @Severity(value = SeverityLevel.NORMAL)
    @Issue("TK-15254")
    @Stories("Positive Login Test")
    @Test
    public void loginPositive() {
        loginPage = BasePage.create(dr, LoginPage.class);
        loginPage.openPage();
        makeScreenshot();
        messageAttachment("Hi this is ");
        homePage = loginPage.loginAs(PropertyLoader.getTestProperty("testUser"),
                                    PropertyLoader.getTestProperty("testPassword"));
        assertTrue(homePage.isLoggedIn());
    }

    @Attachment("Attachment")
    public byte[] makeScreenshot() {
        return ((TakesScreenshot) dr).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment("Exist message")
    public String messageAttachment(String body) {
        return body;
    }

}
