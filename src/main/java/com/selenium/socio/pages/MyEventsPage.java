package com.selenium.socio.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class MyEventsPage extends BasePage {

    /**
     * Constructor
     */
    public MyEventsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Web Elements
     */
    By testAutoAssessmentEvent = By.xpath("//*[contains(text(),'Test Automation Assessment Event')]");
    By menuEventApp = By.id("event-app-nav");
    By menuItemMyEvents = By.cssSelector("[role='menu'] [title='My Events']");
    By menuWebApp = By.cssSelector("[class='rs-sidebar-nav'] [class='gcon gcon-globe rs-icon-menu']");
    By enableWebAppYes = By.cssSelector("[class='form-group'] [class*='bootstrap-switch-on']");
    By enableWebAppNo = By.cssSelector("[class='form-group'] [class*='bootstrap-switch-off']");
    By btnSave = By.cssSelector("[class='btn btn-success']");
    By btnGetSherableLink = By.xpath("//button[contains(text(),'Get Shareable Link')]");
    By txtCopyLink = By.cssSelector("[class='modal-content'] [class='form-control']");
    By btnClose = By.cssSelector("[class='modal-header'] [type='button']");
    By menuEditEvent = By.cssSelector("[data-testid='sidebarItemEditCommunity']");
    By areaWall = By.id("Wall");
    By btnEditFeature = By.cssSelector("[data-testid='buttonEditFeature']");
    By rowInTable = By.cssSelector("[id='componentItemsList'] [data-testid*='row']");

    /**
     * Page Methods
     */
    @Step("Test Automation Assessment Event Step...")
    public MyEventsPage clickTestAutoAssessmentEvent() {
        if (!isElementExist(testAutoAssessmentEvent, 20)) {
            click(menuEventApp);
            click(menuItemMyEvents);
        }

        click(testAutoAssessmentEvent);
        log.info("Click Test Automation Assessment Event");
        return this;
    }

    @Step("Open Web App Tab Step...")
    public MyEventsPage clickWebAppTab() {
        click(menuWebApp);
        log.info("Click Web App Tab.");
        return this;
    }

    @Step("Enable Web App Step...")
    public MyEventsPage enableWebApp() {
        if (!isElementExist(enableWebAppYes, 15)) {
            click(enableWebAppNo);
            log.info("Click Enable Web App.");
        }

        return this;
    }

    @Step("Click Save Button Step...")
    public MyEventsPage clickSave() {
        click(btnSave);
        log.info("Click Save button.");
        return this;
    }

    @Step("Click Get Shareable Link Button Step...")
    public MyEventsPage clickGetShareableLink() {
        moveToElement(btnGetSherableLink);
        click(btnGetSherableLink);
        log.info("Click Get Shareable Link button.");
        return this;
    }

    @Step("Get Copy Link Step...")
    public HomePage getCopyLink() {
        copyLink = getValueOfElement(txtCopyLink);
        log.info("Copy link:" + copyLink);
        click(btnClose);
        return new HomePage(driver);
    }

    @Step("Open Edit Event Menu Step...")
    public MyEventsPage clickEditEventTab() {
        click(menuEditEvent);
        log.info("Click Edit Event Menu.");
        return this;
    }

    @Step("Open Edit Event Menu Step...")
    public MyEventsPage clickWallFeatureEditButton()  {
        moveToElement(areaWall);
        click(areaWall);
        click(btnEditFeature);
        log.info("Move to eleentu.");
        return this;
    }

    @Step("Assert if the post is in the list Step...")
    public MyEventsPage assertIfThePostIsInTheList() {
        log.info("Post title:" + comment);

        List<WebElement> elementList = findElements(rowInTable);
        boolean status = false;

        for (int i = 0; i < elementList.size(); i++) {
            if (elementList.get(i).getText().contains(comment)) {
                log.info("There is post in the list. Post title:" + comment);
                status = true;
                break;
            }
        }

        Assert.assertEquals(status, true, "There is no post in the list.");

        log.info("Assert if the post is in the list.");
        return this;
    }

}