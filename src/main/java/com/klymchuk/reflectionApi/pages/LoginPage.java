package com.klymchuk.reflectionApi.pages;

import com.klymchuk.reflectionApi.core.BasePage;
import com.klymchuk.reflectionApi.staticData.PageTitle;
import com.klymchuk.reflectionApi.staticData.PageUrl;
import org.openqa.selenium.WebDriver;

/**
 * Created by iklymchuk on 02.02.16.
 */
public class LoginPage extends BasePage {

    public LoginPage (WebDriver driver) {
        super(driver, PageTitle.LOGIN_PAGE, PageUrl.LOGIN_PAGE);
    }
}
