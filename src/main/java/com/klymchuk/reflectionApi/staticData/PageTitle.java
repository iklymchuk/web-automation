package com.klymchuk.reflectionApi.staticData;

/**
 * Created by iklymchuk on 01.02.16.
 */
public enum PageTitle {

    EMPTY(""),
    LOGIN_PAGE("ONLINE STORE › Log In"),
    HOME_PAGE(""),
    ACCOUNT_PAGE("");

    private String pageTitle;

    private PageTitle (String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPageTitle() {
        return pageTitle;
    }
}
