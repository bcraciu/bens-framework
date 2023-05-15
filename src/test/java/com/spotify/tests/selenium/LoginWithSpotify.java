package com.spotify.tests.selenium;

import com.spotify.pageObjects.HomePage;
import com.spotify.pageObjects.LoginPage;
import com.spotify.utilities.HelperMethods;
import com.spotify.utilities.ManageDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class LoginWithSpotify extends ManageDriver {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(LoginWithSpotify.class);
    @Autowired
    private HelperMethods helperMethods;
    @Value("${spotify.base.url}")
    private String baseUrl;
    @Value("${login.page.url}")
    private String loginURL;
    @Value("${email}")
    private String email;
    @Value("${pw}")
    private String pw;

    @Given("^User has access to Spotify$")
    public void userHasAccess() {
        getDriver().get(baseUrl);
        Assert.assertEquals("You are on the right page", baseUrl, driver.getCurrentUrl());
        HomePage hp = new HomePage(driver);
        wait.until(ExpectedConditions.visibilityOf(hp.getBanner()));
        hp.getCookieButton().click();
        hp.getLoginButton().click();
        wait.until(ExpectedConditions.urlToBe(loginURL));
        Assert.assertEquals(loginURL, driver.getCurrentUrl());
    }

    @When("User logs in with spotify credentials")
    public void userLogsInWithSpotify() {
        LoginPage lp = new LoginPage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(lp.getEmail()));
        lp.getEmail().sendKeys(email);
        lp.getPass().sendKeys(pw);
        lp.getSpotifyLoginButton().click();
        closeDriver();
    }

    @Then("^Spotify Login is working as expected$")
    public void userIsLoggedIn() {
    }
}
