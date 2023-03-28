package com.spotify.tests.selenium;

import com.spotify.pageObjects.HomePage;
import com.spotify.pageObjects.LoginPage;
import com.spotify.utilities.HelperMethods;
import com.spotify.utilities.ManageDriver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class LoginWithGoogle extends ManageDriver {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(LoginWithSpotify.class);

    @Autowired
    private HelperMethods helperMethods;
    @Value("${spotify.base.url}")
    private String baseUrl;
    @Value("${login.page.url}")
    private String loginURL;
    @Value("${googleUser}")
    private String gUser;
    @Value("${googlePass}")
    private String gPw;

    @When("User logs in with Google credentials")
    public void userLogsInWithGoogle() throws InterruptedException {
        getWait();
        //getDriver().get(loginURL);
        Assert.assertEquals("You are on the right page", loginURL, driver.getCurrentUrl());
        LoginPage lp = new LoginPage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(lp.getGoogleOption()));
        lp.getGoogleOption().click();
        boolean checkAccount = driver.getCurrentUrl().contains("accounts.google.com");
        Assert.assertTrue(checkAccount);

        wait.until(ExpectedConditions.visibilityOf(lp.getGEmail()));
        lp.getGEmail().sendKeys(gUser);
        wait.until(ExpectedConditions.elementToBeClickable(lp.mailNextButton()));
        //Thread.sleep(6000);
        lp.mailNextButton().click();
        Thread.sleep(4000);
        wait.until(ExpectedConditions.visibilityOf(lp.getGPass()));
        lp.getGPass().sendKeys(gPw);
        wait.until(ExpectedConditions.elementToBeClickable(lp.passNextButton()));
        lp.passNextButton().click();

        wait.until(ExpectedConditions.urlToBe(baseUrl));
        Assert.assertEquals("You are back on the Home page", baseUrl, driver.getCurrentUrl());
        HomePage hp = new HomePage(driver);
        Thread.sleep(4000);
        wait.until(ExpectedConditions.visibilityOf(hp.getBanner()));
        hp.getCookieButton().click();
        Assert.assertEquals("Jhonny Joe", hp.getUser().getText());
        closeDriver();
    }

    @Then("^Google Login is working as expected")
    public void userIsLoggedIn() {
    }
}
