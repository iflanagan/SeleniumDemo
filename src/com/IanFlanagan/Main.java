package com.IanFlanagan;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
// import com.saucelabs.saucerest;
import java.net.URL;
import java.util.Random;

public class Main {

    private static RemoteWebDriver driver;

    public static void main(String[] args) throws Exception {

        // define an array of chrome browser versions
        String[] myConfig = {"latest","latest-1","latest-2"};

        Random rand = new Random();
        // Generate random integers in range 0 to 2
        int myInt = rand.nextInt(myConfig.length);
        System.out.println(myInt);
        System.out.println("\nChrome Browser Version: " +myConfig[myInt]);

        System.out.println("\nStarting Automated Run now");

        System.out.println("\nSetting capabilities");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", "Windows 10");
        // caps.setCapability("version", "latest");
        caps.setCapability("version", myConfig[myInt]);
        caps.setCapability("tag", "Testim.io.Demo");
        caps.setCapability("name", "Desktop_Web_testim.io");
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("version", "latest");
        caps.setCapability("extendedDebugging", true);
        caps.setCapability("capturePerformance", true);
        caps.setCapability("build", 1);
        //  caps.setCapability("TunnelIdentifier", "imfaus_tunnel_id"); // need to have a tunnel setup first otherwise comment out

        System.out.println("Capabilities: " +caps);

        RemoteWebDriver driver = new RemoteWebDriver(new URL(MyConfig.URL), caps);

        // SauceREST sauce = new SauceREST(MyConfig.USERNAME, MyConfig.ACCESS_KEY, Datacenter.US);
        //   SauceREST sauce = new SauceREST(MyConfig.USERNAME, MyConfig.ACCESS_KEY);
        //  System.out.println(sauce);

        System.out.println("Starting test");
        sauceContext(driver, "Starting test" );

        SessionId session = ((RemoteWebDriver)driver).getSessionId();
        String currentSessionID = session.toString();
        System.out.println("Session id: " +currentSessionID);

        try
        {

            driver.get(MyConfig.myTestURL);
            sauceContext(driver, "Entering username" );
            System.out.println("\nEntering username: " +MyConfig.testuser);
            driver.findElementByCssSelector(MyElements.userInputField).sendKeys(MyConfig.testuser);

            sauceContext(driver, "Entering password");
            System.out.println("\nEntering Password: " +MyConfig.testpassword);
            driver.findElementByCssSelector(MyElements.passwordInputField).sendKeys(MyConfig.testpassword);

            sauceContext(driver, "Click Login Button");
            System.out.println("\nClick Login Button");
            driver.findElementByCssSelector(MyElements.loginButton).click();

            sauceContext(driver, "Click Hamburger Button");
            System.out.println("\nClick Hamburger Button");
            driver.findElementByCssSelector(MyElements.hamburgerButton).click();

            System.out.println("Get value of logged in user");
            String loggedInuser = driver.findElementByCssSelector(MyElements.logoutButton).getText();
            System.out.println("Value is:  " +loggedInuser);
            Thread.sleep(MyConfig.myDelay);

            System.out.println("\nPerforming Validation");
            sauceContext(driver, "Performing Validation");

            if (loggedInuser.equals(MyConfig.expectedLoginValue)) {

                System.out.println("\nLogin Passed");
                sauceContext(driver, "Login Passed");
                //  sauce.jobPassed(currentSessionID);
                Thread.sleep(MyConfig.myDelay);

            } else {
                System.out.println("\nLogin Failed");
                sauceContext(driver, "Login Failed");
                //  sauce.jobFailed(currentSessionID);
                Thread.sleep(MyConfig.myDelay);
            }

            System.out.println("\nClick Logout button");
            sauceContext(driver, "Click Logout button");
            driver.findElementByCssSelector("#logout_sidebar_link").click();

            sauceContext(driver, "Test Complete!");
            System.out.println("\nTest Complete");

            //  https://app.saucelabs.com/tests/4e733ae452dc4c02a1c13c41984531d0
            System.out.println("\nTest Execution Report URL: https://app.saucelabs.com/tests/" +currentSessionID);

        } catch (Exception e) {

            System.out.printf("\nCan't run test login: " +e.getMessage());
            e.printStackTrace();

        } finally {

            try {

                System.out.println("\nClosing driver now.....");
                driver.quit();

            } catch (Exception e ) {
                System.out.println("\nCan't close driver" +e.getMessage());
                e.printStackTrace();
            }
        }

    }

    public static void example() {

    }
    private static void sauceContext(RemoteWebDriver driver, String text)
    {
        ((JavascriptExecutor)driver).executeScript("sauce:context=" +text);
    }
}
