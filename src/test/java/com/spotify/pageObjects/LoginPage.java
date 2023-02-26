package com.spotify.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    public WebDriver driver;
    By email = By.xpath("//div/input[@data-testid='login-username']");
    By pass = By.cssSelector("#login-password");
    By spotifyLoginButton = By.cssSelector("button#login-button");
    By fbButton = By.xpath("//button[@data-testid='facebook-login']");
    By fbShowCookie = By.xpath("//div[@class='_4t2a']");
    By acceptFbCookie = By.xpath("//button[@data-cookiebanner='accept_button']");
    By fbEmail = By.cssSelector("input#email.inputtext");
    By fbPass = By.xpath("//input[@id='pass']");
    By fbLoginBt = By.xpath("//button[@id='loginbutton']");
    By gMailButton = By.xpath("//button[@data-testid='google-login']");
    By gEmail = By.cssSelector("input#identifierId");
    By emailNext = By.xpath("//div[@id='identifierNext']/div/button[@type='button']");
    By gPass = By.xpath("//div[@class='Xb9hP']/input[@autocomplete='current-password']");
    By passNext = By.xpath("//div[@id='passwordNext']/div/button[@type='button']");
    By appleOption = By.xpath("//button[@data-testid='apple-login']");
    By appleEmail = By.xpath("//input[@id='account_name_text_field']");
    By appleEmailNext = By.xpath("//button[@id='sign-in']");
    By applePass = By.cssSelector("input#password_text_field");
    By appleLoginButton = By.xpath("//button[@id='sign-in']");
    By appleAuthBlock = By.cssSelector("div[id='stepEl']");
    By appleAuthenticationCheck = By.xpath("//div[contains(@class,'verify-phone fade-in')]//div//app-title");
    By digits = By.xpath("//div[@localiseddigit='Digit']/div/div/input");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getEmail() {
        return driver.findElement(email);
    }

    public WebElement getPass() {
        return driver.findElement(pass);
    }

    public WebElement getSpotifyLoginButton() {
        return driver.findElement(spotifyLoginButton);
    }

    public WebElement getFbOption() {
        return driver.findElement(fbButton);
    }

    public WebElement showFbCookie() {
        return driver.findElement(fbShowCookie);
    }

    public WebElement acceptFbCookie() {
        return driver.findElement(acceptFbCookie);
    }

    public WebElement getFbEmail() {
        return driver.findElement(fbEmail);
    }

    public WebElement getFbPass() {
        return driver.findElement(fbPass);
    }

    public WebElement getLoginFbButton() {
        return driver.findElement(fbLoginBt);
    }

    public WebElement getGoogleOption() {
        return driver.findElement(gMailButton);
    }

    public WebElement getGEmail() {
        return driver.findElement(gEmail);
    }

    public WebElement mailNextButton() {
        return driver.findElement(emailNext);
    }

    public WebElement getGPass() {
        return driver.findElement(gPass);
    }

    public WebElement passNextButton() {
        return driver.findElement(passNext);
    }

    public WebElement appleOption() {
        return driver.findElement(appleOption);
    }

    public WebElement getAppleEmail() {
        return driver.findElement(appleEmail);
    }

    public WebElement appleEmailNext() {
        return driver.findElement(appleEmailNext);
    }


    public WebElement getApplePass() {
        return driver.findElement(applePass);
    }

    public WebElement getAppleLoginButton() {
        return driver.findElement(appleLoginButton);
    }
    public WebElement getAuthBlock() {
        return driver.findElement(appleAuthBlock);
    }
    public WebElement checkAuthentication() {
        return driver.findElement(appleAuthenticationCheck);
    }
    public WebElement getDigits() {
        return driver.findElement(digits);
    }
}
