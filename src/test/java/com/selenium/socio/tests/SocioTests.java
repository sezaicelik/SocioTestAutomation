package com.selenium.socio.tests;

import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.selenium.socio.pages.HomePage;
import com.selenium.socio.utils.Listeners.TestListener;

@Listeners({TestListener.class})
@Epic("Socio - Regression Tests")
@Feature("Socio Tests")
public class SocioTests extends BrowserStackBaseTest {

    String username = "testautomation@socio.events";
    String password = "ta12345";
    String baseURL = "https://staging.platform.socio.events/";
    String commentStr = "Sezai Çelik Assignment";


    @Test(priority = 0, description = "Socio Test Aumation Assignment Test.")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Description: Socio Test Aumation Assignment Test.")
    @Story("Socio Test Aumation Assignment Test")
    public void SocioTestAumatAssignSezaiCelik() throws InterruptedException {

        HomePage homePage = new HomePage(driver);

        homePage
                .openPlatformStagingPage(baseURL)
                .goToLoginPage()
                .loginToPlatformStaging(username, password)
                .clickTestAutoAssessmentEvent()
                .clickWebAppTab()
                .enableWebApp()
                .clickSave()
                .clickGetShareableLink()
                .getCopyLink()
                .openNewWindow()
                .openAppStagingPage()
                .enterEmailAndContinue(username)
                .enterPassword(password)
                .loginToAppStagingPage()
                .goToWallPage()
                .clickWhatIsOnYourMind()
                .enterCommend(commentStr)
                .addImage()
                .clickSend()
                .controlPostInTheWall()
                .closeWindowAndGoToPreviousWindow()
                .clickEditEventTab()
                .clickWallFeatureEditButton()
                .assertIfThePostIsInTheList();
    }

}