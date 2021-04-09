package com.selenium.socio.pages;

import com.relevantcodes.extentreports.ExtentReports;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.testng.Assert;

import java.util.List;

public class WallPage extends BasePage {
    /**
     * Constructor
     */
    public WallPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Variables
     */

    /**
     * Web Elements
     */
    By textWhatIsYourMind = By.xpath("//div[contains(text(),'What’s on your mind, Test?')]");
    By txtWhatIsYourMind2 = By.cssSelector("[placeholder='What’s on your mind, Test?']");
    By txtUploadFile = By.xpath("//input[@id='fsp-fileUpload']");
    By btnAddImage = By.xpath("//div[contains(text(),'Add Image')]");
    By btnSave = By.cssSelector("[title='Save']");
    By btnUpload = By.cssSelector("[title='Upload']");
    By positionPopupFile = By.cssSelector("[style='position: relative;']");
    By btnSendCancel = By.cssSelector("[style='display: flex; flex-direction: column; justify-content: space-between;'] button");


    @Step("Click What’s on your mind, Test? Step...")
    public WallPage clickWhatIsOnYourMind() {
        click(textWhatIsYourMind);
        log.info("Click What’s on your mind, Test? Step...");
        return this;
    }

    @Step("Enter comment Step...")
    public WallPage enterCommend(String commentStr) throws InterruptedException {
        comment = commentStr + " " + getRandomNumberInRange(1000000, 9999999);
        sendKeys(txtWhatIsYourMind2, comment);
        log.info("Enter " + comment + " comment Step...");
        return this;
    }

    @Step("Upload File Step...")
    public WallPage addImage() throws InterruptedException {
        String username = System.getProperty("user.name");
        log.info("PC Username:" + username);
        click(btnAddImage);
        uploadFile(txtUploadFile, System.getProperty("user.dir")+"\\Joker.jpg");
        click(btnSave);
        click(btnUpload);
        log.info("Image added.");
        return this;
    }

    @Step("Click Send Step...")
    public WallPage clickSend() throws InterruptedException {
        waitForElement(positionPopupFile);
        waitForElement(btnSendCancel);
        List<WebElement> elementList = findElements(btnSendCancel);
        elementList.get(1).click();
        log.info("Click Send Step...");
        return this;
    }

    @Step("Click Send Step...")
    public HomePage controlPostInTheWall() {
        log.info("Post Title:" + comment);
        Assert.assertTrue(isElementExist(By.xpath("//div[contains(text(),'" + comment + "')]"), 30), "There is not post in the Wall.");
        log.info("Click Send Step...");
        return new HomePage(driver);
    }


}