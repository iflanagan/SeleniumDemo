package com.IanFlanagan;

public class MyConfig {

    public static final String USERNAME = "imfaus";
    public static final String ACCESS_KEY = "<SAUCE_ACCESS_KEY>";
    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
    public static final String localSeleniumURL = "http://127.0.0.1:4444/wd/hub";
    public static final String myTestURL = "https://www.saucedemo.com/";
    public static final String expectedLoginValue = "LOGOUT";
    public static final int myDelay = 2000;

    public static final String testuser = "standard_user";
    public static final String testpassword = "secret_sauce";


}
