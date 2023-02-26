package com.spotify.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    public WebDriver driver;
    By banner = By.cssSelector("div#onetrust-banner-sdk");
    By cookieButton = By.id("onetrust-accept-btn-handler");
    By loginButton = By.xpath("//button[@data-testid='login-button']");
    //By userButton = By.xpath("/html[1]/body[1]/div[4]/div[1]/div[2]/div[1]/header[1]/button[2]");
    By userButton = By.xpath("//button[@data-testid='user-widget-link']/child::span");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getBanner() {
        return driver.findElement(banner);
    }

    public WebElement getCookieButton() {
        return driver.findElement(cookieButton);
    }

    public WebElement getLoginButton() {
        return driver.findElement(loginButton);
    }

    public WebElement getUser() {
        return driver.findElement(userButton);
    }
}
