package com.klymchuk.reflectionApi.core;

import com.google.common.base.Function;
import com.klymchuk.reflectionApi.annotations.Component;
import com.klymchuk.reflectionApi.components.Button;
import com.klymchuk.reflectionApi.components.Checkbox;
import com.klymchuk.reflectionApi.components.InputField;
import com.klymchuk.reflectionApi.components.Table;
import com.klymchuk.reflectionApi.staticData.PageTitle;
import com.klymchuk.reflectionApi.staticData.PageUrl;
import com.klymchuk.reflectionApi.util.PropertyLoader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

import static  org.testng.Assert.assertEquals;

/**
 * Created by iklymchuk on 01.02.16.
 */
public class BasePage extends BaseComponent {

    private final PageTitle pageTitle;
    private final PageUrl pageUrl;

    @Component
    private Button buttons;

    @Component
    private Checkbox checkbox;

    @Component
    private InputField inputField;

    @Component
    private Table table;

    public BasePage(WebDriver driver, PageTitle pageTitle, PageUrl pageUrl) {
        super(driver);
        this.pageTitle = pageTitle;
        this.pageUrl = pageUrl;
    }

    public Button getButton() {
        return buttons;
    }

    public Checkbox getCheckbox() {
        return checkbox;
    }

    public InputField getInputField() {
        return inputField;
    }


    public String getTitle() {
        return pageTitle.getPageTitle();
    }

    public String getUrl() {
        return pageUrl.getPageUrl();
    }

    public boolean isPageOpened() {
        return getTitle().equals(driver.getTitle());
    }

    public void openPage() {
        driver.get(getUrl());
        assertEquals(driver.getTitle(), getTitle());
    }

    public static <T extends BasePage> T create(WebDriver driver, Class<T> pageClass) {
        return CustomPageFactory.init(driver, pageClass);
    }

    public static <T extends BasePage> T init(T page) {
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(page.driver, Integer.parseInt(PropertyLoader.getTestProperty("wait.time.sec")));
        CustomPageFactory.initWithFactory(finder, page.driver, page);
        return page;
    }

    public boolean isElementVisible(WebElement locator) {
        try {
            locator.isDisplayed();
            return true;
        } catch (ElementNotVisibleException ignored) {
            return false;
        } catch (NoSuchElementException ignored) {
            return false;
        } catch (StaleElementReferenceException ignored) {
            return false;
        }
    }

    public boolean isElementVisible(By by) {
        try {
            driver.findElement(by).isDisplayed();
            return true;
        } catch (NoSuchElementException ignored) {
            return false;
        } catch (ElementNotVisibleException ignored) {
            return false;
        } catch (StaleElementReferenceException ignored) {
            return false;
        }
    }

    public boolean isElementPresent(WebElement locator) {
        try {
            locator.getTagName();
        } catch (NoSuchElementException ignored) {
            return false;
        } catch (StaleElementReferenceException ignored) {
            return false;
        }
        return true;
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException ignored) {
            return false;
        } catch (StaleElementReferenceException ignored) {
            return false;
        }
    }

    public boolean isElementTextPresent(By by, String text) {
        try {
            if (driver.findElement(by).getText().equals(text)) {
                return true;
            }
            return false;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementTextPresent(WebElement element, String text) {
        try {
            if (element.getText().equals(text)) {
                return true;
            }
            return false;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isTextPresentOnThePage(String text) {
        return driver.findElement(By.tagName("body")).getText().contains(text);
    }

    public void close() {
        driver.close();
    }

    public void acceptConfirmation() {
        driver.switchTo().alert().accept();
    }

    protected Function<WebDriver, Boolean> visibilityOfElement(final WebElement element) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return element.isDisplayed();
            }
        };
    }

    protected Function<WebDriver, Boolean> hiddentOfElement(final WebElement element) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver arg0) {
                return !element.isDisplayed();
            }
        };
    }
}
