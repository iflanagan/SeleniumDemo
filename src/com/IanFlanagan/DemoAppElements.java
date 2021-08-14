package com.IanFlanagan;

import org.openqa.selenium.By;

import java.util.Random;

public class DemoAppElements {

    // test specific data
    public static class testData {

        public static final String testURL = "http://demo.testim.io/";
        public static final String testUser = "john@testim.io";
        public static final String testPassword = getSaltString(10);
        public static final String expectedText = "HELLO, JOHN";
        public static final String expectedTitle = "Automated Functional Testing - Software Testing Tool - Testim.io";
        public static final String testimURL = "https://www.testim.io/";

        public static String getSaltString(int length) {
            if (length < 0 || length > 10) {
                System.out.println("Choose a value greater than 1 and less than 10");
            }
            String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            StringBuilder salt = new StringBuilder();
            Random rnd = new Random();
            while (salt.length() <= length) { // length of the random string.
                int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                salt.append(SALTCHARS.charAt(index));
            }
            String saltStr = salt.toString();
            // System.out.println("Password Created was: " +saltStr);
            return saltStr;

        }

    }

    // Objects on the page
    public static final String loginLink = "#app > div > header > div > div:nth-child(2) > ul > li > button";
    public static final String userInput = "#login > div:nth-child(1) > input";
    public static final String passwordInput = "#login > div:nth-child(2) > input";
    public static final String loginBtn = "#app > div > section.Login__login___3HOEm > div > div.flexboxgrid__col-xs-6___1DhV6.Login__card-box___1pKg0 > div > nav > button.theme__button___1iKuo.LoginButton__button___1Sd3Q.theme__raised___ONZv6.LoginButton__raised___1fUxJ.theme__primary___2NhN1.LoginButton__primary___38GOe";
    public static final String signedInusertext = "#app > div > header > div > div:nth-child(2) > ul > div > button";
    public static final String logoutlink = "#app > div > header > div > div:nth-child(2) > ul > div > ul > li > a";

}