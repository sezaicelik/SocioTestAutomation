package com.selenium.socio.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    By btnLogIn = By.cssSelector("[data-testid='linkLogin']");
    By btnWall = By.xpath("//span[contains(text(),'Wall')]");


    @Step("Open Platform Stating Page Step...")
    public HomePage openPlatformStagingPage(String baseURL) {
        navigateTo(baseURL);
        log.info("Open Platform Stating Page Step...");
        return this;
    }

    @Step("Go to Login Page Step...")
    public LoginPage goToLoginPage() {
        click(btnLogIn);
        log.info("Go to Login Page Step...");
        return new LoginPage(driver);
    }

    @Step("Open New Window Step...")
    public HomePage openNewWindow() {
        mainWindow = driver.getWindowHandle();
        log.info("Main window handle is " + mainWindow);

        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.manage().window().maximize();
        log.info("Switch to new window.");

        log.info("Open New Window Step...");
        return this;
    }

    @Step("Open Copy Link Page Step...")
    public LoginPage openAppStagingPage() {
        navigateTo(copyLink);
        log.info("Navigate to url:" + copyLink);
        log.info("Open Copy Link Page Step.");
        return new LoginPage(driver);
    }

    @Step("Go to Wall Page Step...")
    public WallPage goToWallPage() {
        click(btnWall);
        log.info("Go to Wall Page Step...");
        return new WallPage(driver);
    }

    @Step("Close Window And Go To Previous Window Step...")
    public MyEventsPage closeWindowAndGoToPreviousWindow() {
        driver.close();
        log.info("Close second window.");
        driver.switchTo().window(mainWindow);
        log.info("Switch to Previous window.");
        return new MyEventsPage(driver);
    }

}