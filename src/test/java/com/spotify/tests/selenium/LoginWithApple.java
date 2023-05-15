package com.spotify.tests.selenium;

import com.spotify.pageObjects.LoginPage;
import com.spotify.utilities.HelperMethods;
import com.spotify.utilities.ManageDriver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class LoginWithApple extends ManageDriver {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(LoginWithApple.class);
    @Autowired
    private HelperMethods helperMethods;
    @Value("${spotify.base.url}")
    private String baseUrl;
    @Value("${login.page.url}")
    private String loginURL;
    @Value("${apple}")
    private String appleUser;
    @Value("${applePW}")
    private String applePw;
    LoginPage lp = new LoginPage(driver);

    @When("User logs in with Apple credentials")
    public void userLogsInWithApple() throws InterruptedException {
        getWait();
        Assert.assertEquals("You are on the right page", loginURL, driver.getCurrentUrl());
        //wait.until(ExpectedConditions.elementToBeClickable(lp.appleOption()));

        lp.appleOption().click();
        Assert.assertTrue(driver.getCurrentUrl().contains("appleid.apple.com"));
        wait.until(ExpectedConditions.visibilityOf(lp.getAppleEmail()));
        lp.getAppleEmail().sendKeys(appleUser);

        lp.appleEmailNext().click();
        wait.until(ExpectedConditions.visibilityOf(lp.getApplePass()));
        //Thread.sleep(6000);
        lp.getApplePass().sendKeys(applePw);
        wait.until(ExpectedConditions.visibilityOf(lp.getAppleLoginButton()));
        lp.getAppleLoginButton().click();
        wait.until(ExpectedConditions.visibilityOf(lp.getAuthBlock()));
        Thread.sleep(4000);
        Assert.assertTrue(lp.checkAuthentication().isDisplayed());
        List<WebElement> digits = driver.findElements((By.xpath("//div[@localiseddigit='Digit']/div/div/input")));
        String[] test = {"1", "2", "3", "4", "5", "6"};
        for (int i = 0; i < digits.size(); i++) {
            digits.get(i).sendKeys(test[i]);
        }
        Thread.sleep(4000);
    }

    @Then("^Apple Login is failing as expected$")
    public void loginFails() {
        wait.until(ExpectedConditions.visibilityOf(lp.getError()));
        LOG.info(lp.getError().getText());
        Assert.assertTrue(lp.getError().isDisplayed());
        Assert.assertEquals("Error message is displayed as expected", "Incorrect verification code.", lp.getError().getText());
    }
}
