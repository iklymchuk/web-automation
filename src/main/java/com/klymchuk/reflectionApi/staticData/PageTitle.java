package com.klymchuk.reflectionApi.staticData;

/**
 * Created by iklymchuk on 01.02.16.
 */
public enum PageTitle {

    EMPTY(""),
    HOME_PAGE("Home Page"),
    LOGIN_PAGE("Login Page"),
    ACCOUNT_PAGE("Account Page");

    private String pageTitle;

    private PageTitle (String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPageTitle() {
        return pageTitle;
    }
}
