package com.klymchuk.reflectionApi.staticData;

/**
 * Created by iklymchuk on 01.02.16.
 */
public enum PageUrl {

    HOME_PAGE(""),
    LOGIN_PAGE(""),
    ACCOUNT_PAGE("");

    private String pageUrl;

    private PageUrl (String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getPageUrl() {
        return pageUrl;
    }
}
