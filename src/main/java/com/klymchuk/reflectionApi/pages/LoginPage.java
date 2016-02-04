package com.klymchuk.reflectionApi.pages;

import com.klymchuk.reflectionApi.core.BasePage;
import com.klymchuk.reflectionApi.staticData.PageTitle;
import com.klymchuk.reflectionApi.staticData.PageUrl;
import com.klymchuk.reflectionApi.util.LocatorsLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by iklymchuk on 02.02.16.
 */
public class LoginPage extends BasePage {

    public static final By USER_NAME_FIELD = LocatorsLoader.get("loginPage.userName");
    public static final By PASSWORD_FIELD = LocatorsLoader.get("loginPage.password");
    public static final By SUBMIT_BUTTON = LocatorsLoader.get("loginPage.submit");

    public LoginPage (WebDriver driver) {
        super(driver, PageTitle.LOGIN_PAGE, PageUrl.LOGIN_PAGE);
    }

    public HomePage loginAs(String user, String password) {
        driver.findElement(USER_NAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(SUBMIT_BUTTON).click();
        return BasePage.create(driver, HomePage.class);
    }
}
