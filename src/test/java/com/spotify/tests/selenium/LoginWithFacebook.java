package com.spotify.tests.selenium;

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

public class LoginWithFacebook extends ManageDriver {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(LoginWithFacebook.class);
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

    @When("User logs in with Facebook credentials")
    public void userLogsInWithFacebook() {
        getWait();
        Assert.assertEquals("You are on the right page", loginURL, driver.getCurrentUrl());
        LoginPage lp = new LoginPage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(lp.getFbOption()));
        lp.getFbOption().click();
        wait.until(ExpectedConditions.visibilityOf(lp.showFbCookie()));
        wait.until(ExpectedConditions.elementToBeClickable(lp.acceptFbCookie()));
        lp.acceptFbCookie().click();
        boolean checkAccount = driver.getCurrentUrl().contains("facebook");
        Assert.assertTrue(checkAccount);
        lp.getFbEmail().sendKeys(email);
        lp.getFbPass().sendKeys(pw);
        lp.getLoginFbButton().click();
        closeDriver();
    }

    @Then("^Facebook Login is working as expected$")
    public void userIsLoggedIn() {
    }
}
