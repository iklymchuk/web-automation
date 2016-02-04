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
public class HomePage extends BasePage {

    public static final By SIGN_OUT_LINK = LocatorsLoader.get("homePage.signOutLink");

    public HomePage(WebDriver driver) {
        super(driver, PageTitle.HOME_PAGE, PageUrl.HOME_PAGE);
    }

    public Boolean isLoggedIn() {
        return driver.findElement(SIGN_OUT_LINK).isDisplayed();
    }


}
