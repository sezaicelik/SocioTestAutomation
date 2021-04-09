package com.selenium.socio.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    By txtEmail = By.id("email");
    By txtPassword = By.id("password");
    By btnLogin = By.cssSelector("[data-testid='btnLogin']");
    By btnContinue = By.id("continue-button");
    By txtPassword2 = By.id("password-input");
    By btnLogin2 = By.id("login-button");


    @Step("Login Step with username: {0}, password: {1}, for method: {method} step...")
    public MyEventsPage loginToPlatformStaging(String username, String password) throws InterruptedException {
        sendKeys(txtEmail, username);
        sendKeys(txtPassword, password);
        click(btnLogin);
        log.info("Login successfully. Username: " + username + " Password:" + password);
        return new MyEventsPage(driver);
    }

    @Step("Login Step with username: {0} and click Continue button step...")
    public LoginPage enterEmailAndContinue(String email) throws InterruptedException {
        sendKeys(txtEmail, email);
        click(btnContinue);
        log.info("Enter "+email+ " mail and click continue button.");
        return this;
    }

    @Step("Login Step with password: {0} step...")
    public LoginPage enterPassword(String password) throws InterruptedException {
        sendKeys(txtPassword2, password);
        log.info("Enter "+password+ " password.");
        return this;
    }
    @Step("Login to App Staging step...")
    public HomePage loginToAppStagingPage() {
        click(btnLogin2);
        log.info("Login to App Staging Page step...");
        return new HomePage(driver);
    }
}