package com.selenium.socio.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;
    public static final int DEFAULT_WAIT_LOADERBOX = 90;
    public static final int DEFAULT_WAIT = 60;
    protected static final Logger log = LogManager.getLogger(BasePage.class.getName());
    public static String mainWindow;
    public static String copyLink = "";
    public static String comment = "";

    //Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    //Wait Wrapper Method
    public void waitVisibility(By elementBy) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
    }

    public void navigateTo(String url) {
        try {
            driver.get(url);
            driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
            log.info("Web application launched");
        } catch (Exception e) {
            log.error("Error while getting app url : " + e);
            throw new RuntimeException(e);
        }
    }

    protected WebElement findElement(By by, int... index) throws InterruptedException {

        WebElement element = null;
        untilElementAppear(by);
        try {
            if (index.length == 0)
                element = driver.findElement(by);
            else
                element = driver.findElements(by).get(index[0]);

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);arguments[0].focus();",
                    element);
            // ((JavascriptExecutor)
            // driver).executeScript("arguments[0].focus();", element);
            // wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            log.error("Error while clicking webelement : " + e);
            throw new RuntimeException(e);
        }
        return element;
    }

    protected void untilElementAppear(By by) {

        try {
            waitLoaderBox(DEFAULT_WAIT_LOADERBOX);
            // driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
            // wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Exception e) {
            log.error("Error while waiting until element appears : " + e);
            throw new RuntimeException(e);
        }
    }

    protected List<WebElement> findElements(By by) {
        List<WebElement> webElements = null;
        untilElementAppear(by);
        try {
            webElements = driver.findElements(by);
        } catch (Exception e) {
            log.error("Error while listing webelements by css selector : " + e);
            throw new RuntimeException(e);
        }
        return webElements;
    }

    public void waitLoaderBox(int time) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[starts-with(@alt,'Loading')]")));
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[alt='Loading...']")));
        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT, TimeUnit.SECONDS);
    }

    protected void click(By by, int... index) {

        WebElement element;
        try {
            element = findElement(by, index);
            //String elemText = element.getText();
            element.click();
            //LogPASS("Click Button : " + elemText);

        } catch (Exception e) {
            log.error("Error while clicking webelement : " + e);
            throw new RuntimeException(e);
        }
    }

    protected void click(By by, boolean clickable) throws InterruptedException {
        try {
            if (!clickable)
                click(by);
            else {
                wait.until(ExpectedConditions.visibilityOfElementLocated(by));
                WebElement elem = wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
                // String elemText = elem.getText();
                elem.click();
                //LogPASS("Click Button : " + elemText);
            }
        } catch (Exception e) {
            log.error("Error while clicking webelement : " + e);
            throw new RuntimeException(e);
        }
    }

    protected void sendKeys(By by, String text) throws InterruptedException {
        WebElement element = null;
        String elemText = null;
        try {
            element = findElement(by);
            if (element.isEnabled()) {
                //  elemText = element.getText();
                element.sendKeys(text);
            }
            //LogPASS("Value : " + key.toString() + " - SendKeys : " + elemText);
        } catch (Exception e) {
            log.error("Error while filling field : " + e);
            throw new RuntimeException(e);
        }
    }

    protected void sendKeys(By by, String text, int... index) throws InterruptedException {
        sendKeys(by, text, false, index);
    }

    protected void sendKeys(By by, String text, boolean pressEnter, int... index) throws InterruptedException {

        WebElement element = null;
        String elemText = null;
        try {
            element = findElement(by, index);
            if (element.isEnabled()) {
                //   elemText = element.getText();
                element.clear();
                element.sendKeys(text);
                if (pressEnter) {
                    waitLoaderBox(DEFAULT_WAIT_LOADERBOX);
                    element.sendKeys(Keys.ENTER);
                }
            }
            //LogPASS("Value : " + text + " - SendKeys : " + elemText);
        } catch (Exception e) {
            log.error("Error while filling field : " + e);
            throw new RuntimeException(e);
        }
    }

    protected String checkComboboxSelectedValue(By by) throws InterruptedException {
        WebElement element = findElement(by);
        String elemText = null;
        try {
            if (element.isEnabled()) {
                //  elemText = element.getText();
                Select selectBox = new Select(driver.findElement(by));
                return selectBox.getFirstSelectedOption().getText();
                //selectBox.selectByValue(value);
            }
            //LogPASS("Value : " + value + " - SelectComboBox : " + elemText);
        } catch (Exception e) {
            log.error("Error while filling field : " + e);
            throw new RuntimeException(e);
        }
        return "";
    }

    protected void selectCombobox(By by, String value) throws InterruptedException {
        WebElement element = findElement(by);
        // String elemText = null;
        try {
            if (element.isEnabled()) {
                //    elemText = element.getText();
                Select selectBox = new Select(driver.findElement(by));
                selectBox.selectByValue(value);
            }
            //LogPASS("Value : " + value + " - SelectComboBox : " + elemText);
        } catch (Exception e) {
            log.error("Error while filling field : " + value + " - " + e);
            throw new RuntimeException(e);
        }
    }

    protected void isDisplayed(By by) throws InterruptedException {
        WebElement element;
        try {
            element = findElement(by);
            String data = element.getText();
            element.isDisplayed();
        } catch (Exception e) {
            log.error("Error while filling field : " + e);
            throw new RuntimeException(e);
        }
    }

    public void clickJS(By by) throws InterruptedException {

        WebElement element = findElement(by);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);

    }

    protected void selectComboboxByText(By by, String text) throws InterruptedException {
        WebElement element = findElement(by);
        // String elemText = null;
        try {
            if (element.isEnabled()) {
                //   elemText = element.getText();
                Select selectBox = new Select(driver.findElement(by));
                selectBox.selectByVisibleText(text);
            }
            //LogPASS("Value : " + text + " - SelectComboBox : " + elemText);
        } catch (Exception e) {
            log.error("Error while filling field : " + e);
            throw new RuntimeException(e);
        }
    }

    protected void selectComboboxByIndex(By by, int index) throws InterruptedException {
        WebElement element = findElement(by);
        // String elemText = null;
        try {
            if (element.isEnabled()) {
                //       elemText = element.getText();
                Select selectBox = new Select(driver.findElement(by));
                selectBox.selectByIndex(index);
            }
            //LogPASS("Value : " + text + " - SelectComboBox : " + elemText);
        } catch (Exception e) {
            log.error("Error while filling field : " + e);
            throw new RuntimeException(e);
        }
    }

    protected void selectComboboxByTextContains(By by, String text) throws InterruptedException {
        WebElement element = findElement(by);
        //   String elemText = null;
        try {
            if (element.isEnabled()) {
                //      elemText = element.getText();
                Select selectBox = new Select(driver.findElement(by));
                for (int i = 0; i < selectBox.getOptions().size(); i++) {
                    log.info("test" + i + selectBox.getOptions().get(i).getText());
                    if (selectBox.getOptions().get(i).getText().contains(text)) {
                        selectBox.getOptions().get(i).click();
                        break;
                    }
                }
            }
            //LogPASS("Value : " + text + " - SelectComboBox : " + elemText);
        } catch (Exception e) {
            log.error("Error while filling field : " + e);
            throw new RuntimeException(e);
        }
    }

    protected void moveToElement(By by) {
        try {
            Actions action = new Actions(driver);
            WebElement we = driver.findElement(by);
            action.moveToElement(we).build().perform();
        } catch (Exception e) {
            log.error("Error while filling field : " + e);
            throw new RuntimeException(e);
        }
    }

    protected String getTextOfElement(By by, int... index) throws InterruptedException {

        String text = null;

        try {
            if (index.length == 0)
                text = driver.findElement(by).getText();
            else
                text = driver.findElements(by).get(index[0]).getText();
        } catch (Exception e) {
            log.error("Error while getting text of element : " + e);
            throw new RuntimeException(e);
        }
        return text;
    }

    protected String getValueOfElement(By by, int... index) {
        String value = null;

        try {
            if (index.length == 0) {
                value = driver.findElement(by).getAttribute("value");
            } else {
                value = driver.findElements(by).get(index[0]).getAttribute("value");
            }
        } catch (Exception e) {
            log.error("Error while getting text of element : " + e);
            throw new RuntimeException(e);
        }
        return value;
    }

    protected void untilElementDisappear(By by) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (Exception e) {
            log.error("Error while waiting until element disappears : " + e);
            throw new RuntimeException(e);
        }
    }


    public String readText(By elementBy) {
        waitVisibility(elementBy);
        return driver.findElement(elementBy).getText();
    }

    public void assertEquals(By elementBy, String expectedText) {
        waitVisibility(elementBy);
        Assert.assertEquals(readText(elementBy), expectedText);

    }

    protected boolean isElementExist(By by) {
        return isElementExist(by, DEFAULT_WAIT);
    }

    protected boolean isElementExist(By by, int timeSeconds) {

        driver.manage().timeouts().implicitlyWait(timeSeconds, TimeUnit.SECONDS);
        boolean isExist = driver.findElements(by).size() > 0;
        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT, TimeUnit.SECONDS);

        return isExist;
    }

    public void uploadFile(By by, String pathToFile) {
        try {
            driver.findElement(by).sendKeys(pathToFile);
            log.info("File uploaded.");
        } catch (Exception e) {
            log.error("Error in attaching file.s : " + e);
            throw new RuntimeException(e);
        }
    }

    public int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public void waitForElement(By by, int... index) throws InterruptedException {
        waitLoaderBox(DEFAULT_WAIT_LOADERBOX);
        findElement(by, index);
    }

}
