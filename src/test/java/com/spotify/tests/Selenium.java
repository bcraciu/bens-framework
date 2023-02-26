package com.spotify.tests;

import com.spotify.pageObjects.LoginPage;
import com.spotify.utilities.HelperMethods;
import com.spotify.utilities.ManageDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
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

public class Selenium extends ManageDriver {
    @Autowired
    private HelperMethods helperMethods;
    @Value("${spotifyBaseUrl}")
    private String baseUrl;
    @Value("${loginPageURL}")
    private String loginURL;
    @Value("${email}")
    private String email;
    @Value("${pw}")
    private String pw;
    @Value("${googleUser}")
    private String gUser;
    @Value("${googlePass}")
    private String gPw;
    @Value("${apple}")
    private String appleUser;
    @Value("${applePW}")
    private String applePw;

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Selenium.class);

    @Given("^User has access to Spotify$")
    public void userHasAccess() {
//        getDriver().get(baseUrl);
//        Assert.assertEquals("You are on the right page", baseUrl, driver.getCurrentUrl());
//        HomePage hp = new HomePage(driver);
//        wait.until(ExpectedConditions.visibilityOf(hp.getBanner()));
//        hp.getCookieButton().click();
//        hp.getLoginButton().click();
//        wait.until(ExpectedConditions.urlToBe(loginURL));
//        Assert.assertEquals(loginURL, driver.getCurrentUrl());
    }

    @When("User logs in with spotify credentials")
    public void userLogsInWithSpotify() {
//        LoginPage lp = new LoginPage(driver);
//        wait.until(ExpectedConditions.elementToBeClickable(lp.getEmail()));
//        lp.getEmail().sendKeys(email);
//        lp.getPass().sendKeys(pw);
//        lp.getSpotifyLoginButton().click();
//        closeDriver();
    }

    @And("User logs in with facebook credentials")
    public void userLogsInWithFacebook() {
//        getDriver().get(loginURL);
//        Assert.assertEquals("You are on the right page", loginURL, driver.getCurrentUrl());
//        LoginPage lp = new LoginPage(driver);
//        wait.until(ExpectedConditions.elementToBeClickable(lp.getFbOption()));
//        lp.getFbOption().click();
//        wait.until(ExpectedConditions.visibilityOf(lp.showFbCookie()));
//        wait.until(ExpectedConditions.elementToBeClickable(lp.acceptFbCookie()));
//        lp.acceptFbCookie().click();
//        boolean checkAccount = driver.getCurrentUrl().contains("facebook");
//        Assert.assertTrue(checkAccount);
//        lp.getFbEmail().sendKeys(email);
//        lp.getFbPass().sendKeys(pw);
//        lp.getLoginFbButton().click();
//        closeDriver();
    }

    @And("User logs in with google credentials")
    public void userLogsInWithGoogle() throws InterruptedException {
//        getDriver().get(loginURL);
//        Assert.assertEquals("You are on the right page", loginURL, driver.getCurrentUrl());
//        LoginPage lp = new LoginPage(driver);
//        wait.until(ExpectedConditions.elementToBeClickable(lp.getGoogleOption();
//        lp.getGoogleOption().click();
//        boolean checkAccount = driver.getCurrentUrl().contains("accounts.google.com");
//        Assert.assertTrue(checkAccount);
//
//        wait.until(ExpectedConditions.visibilityOf(lp.getGEmail()));
//        lp.getGEmail().sendKeys(gUser);
//        wait.until(ExpectedConditions.elementToBeClickable(lp.mailNextButton()));
//        //Thread.sleep(6000);
//        lp.mailNextButton().click();
//        Thread.sleep(4000);
//        wait.until(ExpectedConditions.visibilityOf(lp.getGPass()));
//        lp.getGPass().sendKeys(gPw);
//        wait.until(ExpectedConditions.elementToBeClickable(lp.passNextButton()));
//        lp.passNextButton().click();
//
//
//        wait.until(ExpectedConditions.urlToBe(baseUrl));
//        Assert.assertEquals("You are back on the Home page", baseUrl, driver.getCurrentUrl());
//        HomePage hp = new HomePage(driver);
//        Thread.sleep(4000);
//        wait.until(ExpectedConditions.visibilityOf(hp.getBanner()));
//        hp.getCookieButton().click();
//        Assert.assertEquals("Jhonny Joe", hp.getUser().getText());
//        closeDriver();
    }

    @And("User logs in with apple credentials")
    public void userLogsInWithApple() throws InterruptedException {
        getDriver().get(loginURL);
        Assert.assertEquals("You are on the right page", loginURL, driver.getCurrentUrl());
        LoginPage lp = new LoginPage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(lp.appleOption()));

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

//        String[] names = {"Last Name", "First Name"};
//        IntStream.range(0, names.length).forEach(idx -> generalList.get(idx).sendKeys(names[idx]));

    @Then("^Login feature is working as expected$")
    public void userIsLoggedIn() {
    }

}
