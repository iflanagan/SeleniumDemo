package com.IanFlanagan;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Random;

/*

Ian Flanagan Testim.io 2021
 */

public class SeleniumTest {

    private static String baseUrl = "http://www.testim.io";
    public static final String expectedTitle = "Automated Functional Testing - Software Testing Tool - Testim.io";
    public static final int delay = 2000;
    public static boolean result = false;
    public static final String chromeDriverLocation = "/Users/ianflanagan/chromedriver";
    public static  WebDriver driver = null;

    /*
      steps to run locally on workstation
      - Download chrome driver
      -download selenium jars from selenium.dev
      -change line 22 to be the path to your chrome driver on windows the line would look like this example
      System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
      if you are on windows and comment out line 26 and uncomment line 24

      This example was done using mac and I did also install selenium to run locally see selenium.dev for info
      on downloads it took 2 min to setup.

     */

    public static void main(String[] args) {


        getOS();

        // System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver",chromeDriverLocation);

        driver = new ChromeDriver();

        try {

            driver.get(DemoAppElements.testData.testURL);
            sleep(delay);
            loginTest(DemoAppElements.testData.testUser, DemoAppElements.testData.testPassword,DemoAppElements.testData.expectedText);
            getTitle(DemoAppElements.testData.testimURL, DemoAppElements.testData.expectedTitle);

        } catch (Exception e) {
            System.out.println("Can't run login test");
        }
        finally {
                try {
                    driver.close();
                    driver.quit();

                } catch (Exception ex) {
                    System.out.println("Cant close the driver." +ex.getMessage());
                }
        }
    }
    public static boolean loginTest(String userName, String password, String expectedloggedInUser) {

        boolean result = false;
        System.out.println("Starting Test Demo Login Test");

        try {

            driver.findElement(By.cssSelector(DemoAppElements.loginLink)).click();
            System.out.println("enter username: " + userName);
            driver.findElement(By.cssSelector(DemoAppElements.userInput)).sendKeys(userName);
            sleep(delay);

            System.out.println("enter password: " + password);
            driver.findElement(By.cssSelector(DemoAppElements.passwordInput)).sendKeys(password);
            sleep(delay);

            System.out.println("Click Login Button");
            driver.findElement(By.cssSelector(DemoAppElements.loginBtn)).click();
            sleep(delay);


            String loggedInUser = driver.findElement(By.cssSelector(DemoAppElements.signedInusertext)).getText();
            System.out.println("Perform validation we want to make sure that our current loggedInuser: " + loggedInUser + " equals " + expectedloggedInUser);

            if (loggedInUser.equals(expectedloggedInUser)) {

                System.out.println("Login Passed!");
                result = true;

            } else {
                System.out.println("Login Failed logged In user was: " + loggedInUser);
            }

            System.out.println("Logout of application now");
            driver.findElement(By.cssSelector(DemoAppElements.signedInusertext)).click();
            driver.findElement(By.cssSelector(DemoAppElements.logoutlink)).click();
            sleep(delay);
            System.out.println("Login Test is completed");

        } catch (Exception ex) {
            System.out.println("Can't execute loginTest() function " +ex.getMessage());
        }
        return result;
    }

    public static boolean getTitle(String testURL, String expectedTitle) {

        driver.get(testURL);
        boolean result = false;

        try {

            System.out.println("Starting GetTitle test");
            String currentTitle = driver.getTitle();

            // validation
            if (currentTitle.equals(expectedTitle)) {
                System.out.println("Get Title Test Passed!");
                result = true;
            } else {
                System.out.println("Get Title Test Failed: current Title is: " +currentTitle+ " expected title: " +expectedTitle);
            }

        } catch (Exception ex) {
            System.out.println("Cant Execute Get Title Test" +ex.getMessage());
        }
        return result;
    }
    public static String getOS() {

        String myCurrentOS = System.getProperty("os.name");

        try
        {

            if (myCurrentOS.equals("Windows")) {
                System.out.println("Running on Windows Actual OS is: " +myCurrentOS);
            } else if (myCurrentOS.equals("Mac OS X")) {
                System.out.println("Running on Mac Actual OS is: " +myCurrentOS);
            } else {
                System.out.println("Running Linux Actual OS is:  " +myCurrentOS);
            }

        } catch (Exception ex) {
            System.out.println("Can't get OS now. " +ex.getMessage());
        }
        return myCurrentOS;
    }
    public static void sleep(int seconds) {
        try {

            Thread.sleep(seconds);
        } catch (Exception ex) {
            System.out.println("Can't run sleep now " +ex.getMessage());
        }
    }

}