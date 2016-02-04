package com.klymchuk.reflectionApi.Tests;

import static org.testng.AssertJUnit.assertTrue;

import com.klymchuk.reflectionApi.core.BasePage;
import com.klymchuk.reflectionApi.pages.HomePage;
import com.klymchuk.reflectionApi.pages.LoginPage;
import com.klymchuk.reflectionApi.util.PropertyLoader;
import org.testng.annotations.Test;

/**
 * Created by iklymchuk on 04.02.16.
 */
public class LoginPositiveTest extends BaseTestCase {

    private LoginPage loginPage;
    private HomePage homePage;

    @Test
    public void loginPositive() {
        loginPage = BasePage.create(getDriver(), LoginPage.class);
        loginPage.openPage();
        homePage = loginPage.loginAs(PropertyLoader.getTestProperty("testUser"),
                                    PropertyLoader.getTestProperty("testPassword"));
        assertTrue(homePage.isLoggedIn());
    }

}
