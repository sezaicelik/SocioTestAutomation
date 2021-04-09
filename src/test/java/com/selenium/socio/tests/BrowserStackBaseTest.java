package com.selenium.socio.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BrowserStackBaseTest {

    public RemoteWebDriver driver;
    //public static final String USERNAME = "sezai2";
    //public static final String AUTOMATE_KEY = "bWkgoq4cQWf3ebrjP9nx";
    public static final String USERNAME = "alihanozbayrak1";
    public static final String AUTOMATE_KEY = "yYzukxkJ3sAqxT1pyW7B";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    @Parameters({"browser", "browser_version", "os", "os_version", "browserstack.selenium_version"})
    @BeforeMethod
    public void setUp(String browserName, String browser_version, String os, String os_version, String browserstack_selenium_version, Method name) {

        System.out.println("browser name is : " + browserName);
        String methodName = name.getName();

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("os", os);
        caps.setCapability("os_version", os_version);
        caps.setCapability("browser_version", browser_version);
        caps.setCapability("name", methodName);
        caps.setCapability("browserstack.selenium_version", browserstack_selenium_version);

        if (browserName.equals("Chrome")) {
            WebDriverManager.chromedriver().setup();
            caps.setCapability("browser", "Chrome");
        } else if (browserName.equals("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            caps.setCapability("browser", "Firefox");
        }
        try {
            driver = new RemoteWebDriver(new URL(URL), caps);
            driver.setFileDetector(new LocalFileDetector());
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }


}
